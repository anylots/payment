package com.frame.payment.dao;

import com.frame.payment.model.OrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anylots
 * @version $Id: OrderRecordDAO.java, v 0.1 2020年10月18日 11:01 anylots Exp $
 */
public interface OrderRecordDAO extends JpaRepository<OrderRecord, Integer> {

}