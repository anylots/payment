package com.frame.payment.common;

import com.alibaba.fastjson.JSON;
import com.frame.payment.common.util.LoggerUtil;
import com.frame.payment.common.util.OrderStatusEnum;
import com.frame.payment.model.OrderRecord;
import com.frame.payment.service.OrderRecordService;
import com.frame.tcctransaction.common.CommonInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author anylots
 * @version $Id: TwoStagesAspect.java, v 0.1 2020年10月17日 16:07 anylots Exp $, chengdu
 */
@Component
@Aspect
public class TwoStagesAspect {

    private static final Set<Long> TIME_INTERVALS = new HashSet<Long>() {
        {
            this.add(200l);
            this.add(400l);
            this.add(800l);
        }
    };

    /**
     * 参与者拦截方法
     *
     * @param joinPoint
     * @param twoStages
     */
    @Around("pointcut() && @annotation(twoStages)")
    public void advice(ProceedingJoinPoint joinPoint, TwoStages twoStages) {

        CommonInfo commonInfo = (CommonInfo) joinPoint.getArgs()[0];
        //一阶段调用
        if (commonInfo.isOnPrepareStage()) {

            //保存参与者信息到线程变量
            Set<TwoStageCompleter> stageCompletes = TwoStagesThreadLocal.get() == null ? new HashSet<>() : TwoStagesThreadLocal.get();
            stageCompletes.add(buildTwoStageCompleter(joinPoint, commonInfo));

            //保存事务订单记录
            OrderRecordService orderRecordService = (OrderRecordService) ApplicationContextGetBeanHelper.getBean(OrderRecordService.class);
            orderRecordService.saveOrderRecord(buildOrderRecord());
        }

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            LoggerUtil.error("tcc invoke error", throwable);
            throw new RuntimeException("tcc invoke error", throwable);
        }
    }

    /**
     * build twoStageCompleter
     *
     * @param joinPoint
     * @param commonInfo
     * @return
     */
    private TwoStageCompleter buildTwoStageCompleter(ProceedingJoinPoint joinPoint, CommonInfo commonInfo) {
        TwoStageCompleter completer = new TwoStageCompleter();
        completer.setTargetClass(joinPoint.getSignature().getClass());
        completer.setMethodName(joinPoint.getSignature().getName());
        completer.setCommonInfo(commonInfo);
        return completer;
    }

    /**
     * build order record
     *
     * @return
     */
    private static OrderRecord buildOrderRecord() {

        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setUserId("system");
        orderRecord.setBizType(ApiContextThreadLocal.get().getBizType());
        orderRecord.setOrderId(ApiContextThreadLocal.get().getOrderId());

        //参与者信息，提供事务恢复服务使用
        List<String> feignList = new ArrayList<>();
        for (TwoStageCompleter completer : TwoStagesThreadLocal.get()) {
            feignList.add(completer.getTargetClass().getSimpleName() + "_" + completer.getMethodName());
        }
        orderRecord.setContext(JSON.toJSONString(feignList));
        orderRecord.setStatus(OrderStatusEnum.INIT.getCode());
        return orderRecord;
    }

    /**
     * 二阶段调用重试
     *
     * @param joinPoint
     */
    private void processRetry(ProceedingJoinPoint joinPoint) {
        for (Long interval : TIME_INTERVALS) {
            try {
                Thread.currentThread().sleep(interval.longValue());
                joinPoint.proceed();
                return;
            } catch (Throwable throwable) {
                LoggerUtil.error("tcc process retry error", throwable);
            }
        }
    }

}