package com.frame.payment.service.impl;

import com.frame.balance.common.request.BalanceReduceInfo;
import com.frame.payment.client.BalanceServiceClient;
import com.frame.payment.common.TwoStages;
import com.frame.payment.common.util.ServiceConstants;
import com.frame.payment.service.BalanceManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * implement of BalanceManageService
 *
 * @author anylots
 * @version $Id: BalanceProcessServiceImpl.java, v 0.1 2020年10月17日 16:40 anylots Exp $, chengdu
 */
@Component
public class BalanceManageServiceImpl implements BalanceManageService {

    @Autowired
    BalanceServiceClient balanceServiceClient;

    /**
     * balance reduce
     *
     * @param reduceInfo
     */
    @TwoStages
    @Override
    public void balanceReduce(BalanceReduceInfo reduceInfo) {

        //step 1. balance reduce
        String result = balanceServiceClient.balanceReduce(reduceInfo);

        //step 2. assertion results
        Assert.isTrue(ServiceConstants.SUCCESS.equals(result), "couponService'couponUse result is fail");
    }
}