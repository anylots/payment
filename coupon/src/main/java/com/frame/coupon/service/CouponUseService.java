package com.frame.coupon.service;

import com.frame.tcctransaction.common.CommonInfo;
import org.springframework.stereotype.Component;

/**
 * @author anylots
 * @version $Id: BalanceReduceServiceImpl.java, v 0.1 2020年10月17日 14:04 anylots Exp $
 */
@Component
public class CouponUseService extends TwoStageCommonService {


    /**
     * 红包核销一阶段prepare方法
     *
     * @param commonInfo
     */
    @Override
    public void prepare(CommonInfo commonInfo) {

    }

    /**
     * 现金核销二阶段commit方法
     *
     * @param commonInfo
     */
    @Override
    public void commit(CommonInfo commonInfo) {

    }

    /**
     * 现金核销二阶段cancel方法
     *
     * @param commonInfo
     */
    @Override
    public void cancel(CommonInfo commonInfo) {

    }
}