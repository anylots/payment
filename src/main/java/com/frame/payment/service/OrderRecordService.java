package com.frame.payment.service;

import com.frame.payment.model.OrderRecord;

/**
 * order record service
 *
 * @author anylots
 * @version $Id: OrderRecordService.java, v 0.1 2020年10月18日 19:00 anylots Exp $
 */
public interface OrderRecordService {

    /**
     * save order record
     *
     * @param orderRecord
     */
    public void saveOrderRecord(OrderRecord orderRecord);

    /**
     * update by order id
     *
     * @param orderRecord
     */
    public void updateByOrderId(OrderRecord orderRecord);

}