package com.frame.payment.common;

import com.frame.tcctransaction.common.CommonInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author anylots
 * @version $Id: TwoStagesAspect.java, v 0.1 2020年10月17日 16:07 anylots Exp $, chengdu
 */
@Component
@Aspect
public class TwoStagesAspect {

    private static Logger logger = LoggerFactory.getLogger(TwoStagesAspect.class);

    private static final Set<Long> TIME_INTERVALS = new HashSet<Long>() {
        {
            this.add(200l);
            this.add(400l);
            this.add(800l);
        }
    };

    @Pointcut("@annotation(com.frame.payment.common.TwoStages)")
    private void pointcut() {
    }

    @Around("pointcut() && @annotation(twoStages)")
    public void advice(ProceedingJoinPoint joinPoint, TwoStages twoStages) {

        CommonInfo commonInfo = (CommonInfo) joinPoint.getArgs()[0];
        if (commonInfo.isOnPrepareStage()) {
            //一阶段调用

            //定义参与者completer(类名、方法名以及参数)
            TwoStageCompleter completer = new TwoStageCompleter();
            completer.setClassName(joinPoint.getSignature().getDeclaringType().getName());
            completer.setMethodName(joinPoint.getSignature().getName());
            completer.setCommonInfo(commonInfo);

            //保存参与者到线程变量
            Set<TwoStageCompleter> stageCompletes = TwoStagesThreadLocal.get() == null ? new HashSet<>() : TwoStagesThreadLocal.get();
            stageCompletes.add(completer);
        }

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("tcc invoke error", throwable);
            throw new RuntimeException("tcc invoke error", throwable);
        }
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
                logger.error("tcc process retry error", throwable);
            }
        }
    }

}