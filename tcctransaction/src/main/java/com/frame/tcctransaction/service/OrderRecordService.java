package com.frame.tcctransaction.service;


import com.frame.tcctransaction.model.OrderRecord;

import java.util.List;

/**
 * @author anylots
 * @version $Id: OrderRecordService.java, v 0.1 2020年10月18日 19:00 anylots Exp $
 */
public interface OrderRecordService {

    /**
     * find by status
     *
     * @param status
     * @return
     */
    public List<OrderRecord> findByStatus(String status);

    /**
     * update by order id
     *
     * @param orderRecord
     */
    public void updateByOrderId(OrderRecord orderRecord);


}