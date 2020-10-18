package com.frame.payment.service.impl;

import com.frame.coupon.common.request.CouponUseInfo;
import com.frame.payment.client.CouponServiceClient;
import com.frame.payment.common.TwoStages;
import com.frame.payment.common.util.ServiceConstants;
import com.frame.payment.service.CouponManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * implement of CouponManageService
 *
 * @author anylots
 * @version $Id: CouponProcessServiceImpl.java, v 0.1 2020年10月17日 16:40 anylots Exp $, chengdu
 */
@Component
public class CouponManageServiceImpl implements CouponManageService {

    @Autowired
    private CouponServiceClient couponServiceClient;

    /**
     * coupon use
     *
     * @param useInfo
     */
    @TwoStages
    @Override
    public void couponUse(CouponUseInfo useInfo) {

        //step 1. coupon use
        String result = couponServiceClient.couponUse(useInfo);

        //step 2. assertion results
        Assert.isTrue(ServiceConstants.SUCCESS.equals(result), "couponService'couponUse result is fail");
    }
}