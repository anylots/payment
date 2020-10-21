package com.frame.payment.service.impl;

import com.frame.payment.dao.OrderRecordDAO;
import com.frame.payment.model.OrderRecord;
import com.frame.payment.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * implement of OrderRecordService
 *
 * @author anylots
 * @version $Id: OrderRecordServiceImpl.java, v 0.1 2020年10月18日 19:03 anylots Exp $
 */
@Component
public class OrderRecordServiceImpl implements OrderRecordService {

    @Autowired
    private OrderRecordDAO orderRecordDAO;

    /**
     * save order record
     *
     * @param orderRecord
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)//发起者记录在单独事务中
    public void saveOrderRecord(OrderRecord orderRecord) {
        orderRecordDAO.save(orderRecord);
    }

    /**
     * update by order id
     *
     * @param orderRecord
     */
    @Override
    public void updateByOrderId(OrderRecord orderRecord) {
        orderRecordDAO.updateByOrderId(orderRecord);
    }
}