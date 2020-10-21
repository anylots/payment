package com.frame.tcctransaction.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.frame.tcctransaction.client.BalanceServiceClient;
import com.frame.tcctransaction.client.CouponServiceClient;
import com.frame.tcctransaction.common.ApplicationContextGetBeanHelper;
import com.frame.tcctransaction.common.CommonInfo;
import com.frame.tcctransaction.common.util.LoggerUtil;
import com.frame.tcctransaction.common.util.OrderStatusEnum;
import com.frame.tcctransaction.model.OrderRecord;
import com.frame.tcctransaction.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * tcc scheduled task
 *
 * @author anylots
 * @version $Id: ScheduledTask.java, v 0.1 2020年10月18日 21:22 anylots Exp $
 */
@Component
public class TccScheduledTask {

    @Autowired
    private OrderRecordService orderRecordService;

    @Autowired
    private BalanceServiceClient balanceServiceClient;

    @Autowired
    private CouponServiceClient couponServiceClient;


    /**
     * 每隔十分钟执行, 单位：ms。
     */
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void executeFixRate() {

        //捞取未完成事务记录
        List<OrderRecord> orderRecords = orderRecordService.findByStatus(OrderStatusEnum.INIT.getCode());

        for (OrderRecord orderRecord : orderRecords) {
            //解析二阶段参与者列表
            String context = orderRecord.getContext();
            List<String> feignList = JSON.parseObject(context, new TypeReference<List<String>>() {
            });

            //依次调用参与者，完成二阶段事务
            for (String feignInfo : feignList) {
                invokeForFeign(feignInfo, buildCommonInfo(orderRecord));
            }

            //更新发起者事务记录表
            orderRecord.setStatus(OrderStatusEnum.COMPLETE.getCode());
            orderRecordService.updateByOrderId(orderRecord);
        }
    }

    /**
     * invoke for feign
     *
     * @param feignInfo
     * @param commonInfo
     */
    private void invokeForFeign(String feignInfo, CommonInfo commonInfo) {

        //feignInfo校验
        if (StringUtils.isEmpty(feignInfo) || !feignInfo.contains("_")) {
            LoggerUtil.error(String.format("feignInfo is not available,orderId=", commonInfo.getOrderId()));
            return;
        }

        Assert.isTrue(!StringUtils.isEmpty(feignInfo) && feignInfo.contains("_"), "feignInfo is not available");

        String feignName = feignInfo.split("_")[0];
        String methodName = feignInfo.split("_")[1];
        //获取feign class
        Class clazz = null;
        try {
            clazz = Class.forName(feignName);
        } catch (ClassNotFoundException e) {
            LoggerUtil.error(String.format("feign class is not found,orderId=", commonInfo.getOrderId()), e);
        }
        //调用参与者提交、回滚
        try {
            Method method = clazz.getMethod(methodName, new Class[]{CommonInfo.class});
            method.invoke(ApplicationContextGetBeanHelper.getBean(clazz), commonInfo);

        } catch (ReflectiveOperationException e) {
            LoggerUtil.error("tcc schedule invoke error", e);
        }
    }

    /**
     * build common info
     *
     * @param orderRecord
     * @return
     */
    private CommonInfo buildCommonInfo(OrderRecord orderRecord) {
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setOrderId(orderRecord.getOrderId());
        commonInfo.setStage(orderRecord.getStatus());
        return commonInfo;
    }

}