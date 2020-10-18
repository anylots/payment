package com.frame.coupon.controller;


import com.frame.coupon.common.ResponseUtil;
import com.frame.coupon.common.request.CouponUseInfo;
import com.frame.coupon.common.result.CouponResult;
import com.frame.coupon.service.TwoStageCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author anylots
 * @version $Id: CouponController.java, v 0.1 2020年10月17日 13:38 anylots Exp $
 */
@RestController
@RequestMapping(value = "couponController")
public class CouponController {

    @Autowired
    private TwoStageCommonService couponUseService;

    @RequestMapping(value = "couponUse")
    public void couponUse(HttpServletRequest request, HttpServletResponse response,
                          @RequestBody CouponUseInfo couponUseInfo) {

        CouponResult result = new CouponResult();
        couponUseService.process(couponUseInfo);

        result.setSuccess(true);
        ResponseUtil.applyResponseData(response, "success");
    }
}