package com.frame.payment.client;

import com.frame.coupon.common.request.CouponUseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author anylots
 * @version $Id: BalanceServiceClient.java, v 0.1 2020年10月17日 13:01 anylots Exp $
 */
@Component
@FeignClient(value = "couponService") //这里的value对应调用服务的spring.applicatoin.name
public interface CouponServiceClient {

    @RequestMapping(value = "/couponController/couponUse")
    String couponUse(@RequestBody CouponUseInfo useInfo);
}