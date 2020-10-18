package com.frame.payment.service;

import com.frame.coupon.common.request.CouponUseInfo;

/**
 * @author anylots
 * @version $Id: CouponProcessService.java, v 0.1 2020年10月17日 16:38 anylots Exp $
 */
public interface CouponManageService {

    /**
     * coupon use
     *
     * @param useInfo
     */
    public void couponUse(CouponUseInfo useInfo);
}