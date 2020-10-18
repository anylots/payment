package com.frame.payment.core;

import java.util.List;
import java.util.Map;

/**
 * @author anylots
 * @version $Id: PaymentService.java, v 0.1 2020年10月17日 14:50 anylots Exp $
 */
public interface PaymentService {

    /**
     * 两阶段支付服务
     *
     * @param payInfo 支付工具信息
     */
    public void payWithTwoStage(List<Map<String, Object>> payInfo);
}