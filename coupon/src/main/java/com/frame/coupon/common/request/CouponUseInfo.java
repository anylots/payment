package com.frame.coupon.common.request;

import com.frame.tcctransaction.common.CommonInfo;

/**
 * @author anylots
 * @version $Id: BalanceManageRequest.java, v 0.1 2020年10月17日 13:48 anylots Exp $
 */
public class CouponUseInfo extends CommonInfo {

    /**
     * account No of user
     */
    private String userId;

    /**
     * amount of money
     */
    private String couponId;

    /**
     * Getter method for property <tt>userId</tt>.
     *
     * @return property value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Setter method for property <tt>userId</tt>.
     *
     * @param userId value to be assigned to property userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter method for property <tt>couponId</tt>.
     *
     * @return property value of couponId
     */
    public String getCouponId() {
        return couponId;
    }

    /**
     * Setter method for property <tt>couponId</tt>.
     *
     * @param couponId value to be assigned to property couponId
     */
    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
}