package com.frame.payment.common;

import com.alibaba.fastjson.JSON;
import com.frame.payment.common.util.LoggerUtil;
import com.frame.payment.common.util.OrderStatusEnum;
import com.frame.payment.common.util.TransactionStatusEnum;
import com.frame.payment.model.OrderRecord;
import com.frame.payment.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * TwoStageStarter
 *
 * @author anylots
 * @version $Id: TwoStageStarter.java, v 0.1 2020年10月18日 15:09 anylots Exp $, chengdu
 */
public class TwoStageStarter {

    @Autowired
    private OrderRecordService orderRecordService;

    /**
     * 开启两阶段提交
     * <p>
     * 两阶段提交TwoStage的启动须放在本地Spring事务中，
     * 且须放在调用外部参与者之前。
     * <p>
     * 在一阶段调用时，TwoStagesAspect拦截器将参与者类名、方法名、参数保存在ThreadLocal中，
     * 在本地事务提交、回滚后，Spring事务同步器将取出一阶段保存的信息，自动调用参与者二阶段方法，完成最终提交/回滚。
     */
    public static void startTwoStage() {

        //本地保存订单信息

        OrderRecordService orderRecordService = (OrderRecordService) ApplicationContextGetBeanHelper.getBean(OrderRecordService.class);
        OrderRecord orderRecord = buildOrderRecord();
        orderRecordService.saveOrderRecord(orderRecord);

        //定义spring事务同步器
        TransactionSynchronizationAdapter tccSynchronizationAdapter = new TransactionSynchronizationAdapter() {

            //在事务提交/回滚后调用
            @Override
            public void afterCompletion(int status) {

                switch (status) {
                    case 0:
                        //transaction status is commit
                        twoPhaseProcess(TransactionStatusEnum.STATUS_COMMITTED.getCode());
                        break;
                    case 1:
                        //transaction status is rollback
                        twoPhaseProcess(TransactionStatusEnum.STATUS_ROLLED_BACK.getCode());
                        break;
                    default:
                        LoggerUtil.error("tcc transaction status is unknown");
                        throw new RuntimeException("tcc transaction status is unknown");
                }

                //更新事务记录为完成状态
                updateTccRecord(orderRecord, orderRecordService);
            }
        };

        //注册spring事务同步器，spring本地事务提交、回滚时会执行事务同步器中对应的方法；
        TransactionSynchronizationManager.registerSynchronization(
                tccSynchronizationAdapter
        );
    }

    /**
     * 第二阶段处理
     *
     * @param stage 提交、回滚
     */
    private static void twoPhaseProcess(String stage) throws RuntimeException {

        //获取一阶段调用时保存的参与者信息
        Set<TwoStageCompleter> stageCompletes = TwoStagesThreadLocal.get();

        if (stageCompletes == null) {
            LoggerUtil.error("stageCompletes is empty");
            return;
        }

        for (TwoStageCompleter completer : stageCompletes) {
            completer.invokeAfterPrepare(stage);
        }
    }

    /**
     * update tcc record
     *
     * @param orderRecord
     * @param orderRecordService
     */
    private static void updateTccRecord(OrderRecord orderRecord, OrderRecordService orderRecordService) {

        //保存参与者信息，提供事务恢复服务使用
        List<String> feignList = new ArrayList<>();
        for (TwoStageCompleter completer : TwoStagesThreadLocal.get()) {
            feignList.add(completer.getTargetClass().getSimpleName() + "_" + completer.getMethodName());
        }
        orderRecord.setContext(JSON.toJSONString(feignList));
        orderRecord.setStatus(OrderStatusEnum.COMPLETE.getCode());
        orderRecordService.updateByOrderId(orderRecord);
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
        orderRecord.setOrderId(UUID.randomUUID().toString());
        orderRecord.setStatus(OrderStatusEnum.INIT.getCode());
        return orderRecord;
    }

}