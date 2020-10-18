package com.frame.payment.service;

import com.frame.payment.model.OrderRecord;

/**
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

}