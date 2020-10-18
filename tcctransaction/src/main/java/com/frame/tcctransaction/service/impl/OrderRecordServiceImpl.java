package com.frame.tcctransaction.service.impl;

import com.frame.tcctransaction.dao.OrderRecordDAO;
import com.frame.tcctransaction.model.OrderRecord;
import com.frame.tcctransaction.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author anylots
 * @version $Id: OrderRecordServiceImpl.java, v 0.1 2020年10月18日 19:03 anylots Exp $
 */
@Component
public class OrderRecordServiceImpl implements OrderRecordService {

    @Autowired
    private OrderRecordDAO orderRecordDAO;


    /**
     * find by status
     *
     * @param status
     * @return
     */
    @Override
    public List<OrderRecord> findByStatus(String status) {
        List<OrderRecord> orderRecords = orderRecordDAO.findByStatus(status);
        return orderRecords;
    }
}