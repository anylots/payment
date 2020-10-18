package com.frame.payment.service;

import com.frame.balance.common.request.BalanceReduceInfo;

/**
 * @author anylots
 * @version $Id: BalanceManageService.java, v 0.1 2020年10月17日 16:36 anylots Exp $
 */
public interface BalanceManageService {

    /**
     * balance reduce
     *
     * @param reduceInfo
     */
    public void balanceReduce(BalanceReduceInfo reduceInfo);
}