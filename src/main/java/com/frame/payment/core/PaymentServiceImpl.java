package com.frame.payment.core;

import com.frame.balance.common.request.BalanceReduceInfo;
import com.frame.coupon.common.request.CouponUseInfo;
import com.frame.payment.common.TwoStageStarter;
import com.frame.payment.service.BalanceManageService;
import com.frame.payment.service.CouponManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * implement of PaymentService
 *
 * @author anylots
 * @version $Id: PaymentServiceImpl.java, v 0.1 2020年10月17日 14:52 anylots Exp $, chengdu
 */
@Component
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private BalanceManageService balanceManageService;

    @Autowired
    private CouponManageService couponManageService;

    /**
     * 两阶段支付服务
     *
     * @param payInfo 支付工具信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payWithTwoStage(List<Map<String, Object>> payInfo) {

        //step1.开启两阶段提交
        TwoStageStarter.startTwoStage();

        //step2.现金扣减
        balanceManageService.balanceReduce(buildBalanceReduceInfo(payInfo));

        //step3.红包使用
        couponManageService.couponUse(buildCouponUseInfo(payInfo));
    }

    /**
     * build balance reduce info
     *
     * @return
     */
    private BalanceReduceInfo buildBalanceReduceInfo(List<Map<String, Object>> payInfo) {
        BalanceReduceInfo reduceInfo = new BalanceReduceInfo();
        reduceInfo.setAccountNo("62175315335");
        reduceInfo.setAmount(80);
        return reduceInfo;
    }

    /**
     * build coupon use info
     *
     * @return
     */
    private CouponUseInfo buildCouponUseInfo(List<Map<String, Object>> payInfo) {
        CouponUseInfo couponUseInfo = new CouponUseInfo();
        couponUseInfo.setUserId("system");
        couponUseInfo.setCouponId("system_1");
        return couponUseInfo;
    }

}