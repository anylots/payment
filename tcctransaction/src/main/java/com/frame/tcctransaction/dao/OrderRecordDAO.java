package com.frame.tcctransaction.dao;

import com.frame.tcctransaction.model.OrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author anylots
 * @version $Id: OrderRecordDAO.java, v 0.1 2020年10月18日 11:01 anylots Exp $
 */
public interface OrderRecordDAO extends JpaRepository<OrderRecord, Integer> {

    /**
     * find by status
     *
     * @param name
     * @return
     */
    public List<OrderRecord> findByStatus(String name);

    /**
     * update by order id
     *
     * @param orderRecord
     */
    public void updateByOrderId(OrderRecord orderRecord);


}