package com.frame.balance.controller;

import com.frame.balance.common.ResponseUtil;
import com.frame.balance.common.request.BalanceReduceInfo;
import com.frame.balance.service.TwoStageCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author anylots
 * @version $Id: BalanceController.java, v 0.1 2020年10月17日 13:38 anylots Exp $
 */
@RestController
@RequestMapping(value = "balanceController")
public class BalanceController {

    @Autowired
    private TwoStageCommonService balanceReduceService;

    @RequestMapping(value = "balanceReduce")
    public void balanceReduce(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody BalanceReduceInfo reduceInfo) {

        System.out.println(reduceInfo.getAccountNo());
        System.out.println(reduceInfo.getStage());

        balanceReduceService.process(reduceInfo);

        ResponseUtil.applyResponseData(response, "success");
    }
}