package com.frame.payment.controller;

import com.frame.payment.common.ApiContext;
import com.frame.payment.common.ApiContextThreadLocal;
import com.frame.payment.common.util.BizTypeEnum;
import com.frame.payment.common.util.ResponseUtil;
import com.frame.payment.common.util.ServiceConstants;
import com.frame.payment.core.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * payment controller
 *
 * @author anylots
 * @version $Id: ServiceController.java, v 0.1 2020年10月17日 13:12 anylots Exp $, chengdu
 */
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * payment with assets
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/paymentWithAssets", method = RequestMethod.GET)
    public void paymentWithAssets(HttpServletRequest request, HttpServletResponse response) {

        //step 1. check parameter
        /**do nothing */


        //init apiContext
        ApiContext apiContext = new ApiContext();
        apiContext.setBizType(BizTypeEnum.PAYMENT.getCode());
        ApiContextThreadLocal.set(apiContext);

        //step 2. pay with two stage
        paymentService.payWithTwoStage(new ArrayList<>());

        //step 3. return result
        ResponseUtil.applyResponseData(response, ServiceConstants.SUCCESS);
    }

}