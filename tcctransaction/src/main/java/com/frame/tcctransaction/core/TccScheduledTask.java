package com.frame.tcctransaction.core;

import com.frame.tcctransaction.client.BalanceServiceClient;
import com.frame.tcctransaction.common.CommonInfo;
import com.frame.tcctransaction.model.OrderRecord;
import com.frame.tcctransaction.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author anylots
 * @version $Id: ScheduledTask.java, v 0.1 2020年10月18日 21:22 anylots Exp $
 */
@Component
public class TccScheduledTask {

    @Autowired
    private OrderRecordService orderRecordService;

    @Autowired
    private BalanceServiceClient balanceServiceClient;

    /**
     * 每隔十分钟执行, 单位：ms。
     */
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void testFixRate() {
        List<OrderRecord> orderRecords = orderRecordService.findByStatus("INIT");
        for (OrderRecord orderRecord : orderRecords) {
            //TODO
            CommonInfo commonInfo = new CommonInfo();
            commonInfo.setOrderId(orderRecord.getOrderId());
            balanceServiceClient.balanceReduce(commonInfo);
        }
    }
}