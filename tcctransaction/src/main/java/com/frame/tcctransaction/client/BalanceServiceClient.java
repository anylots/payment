package com.frame.tcctransaction.client;

import com.frame.tcctransaction.common.CommonInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author anylots
 * @version $Id: BalanceServiceClient.java, v 0.1 2020年10月17日 13:01 anylots Exp $
 */
@Component
@FeignClient(value = "balanceService") //这里的value对应调用服务的spring.application.name
public interface BalanceServiceClient {

    @RequestMapping(value = "/balanceController/balanceReduce")
    String balanceReduce(@RequestBody CommonInfo reduceInfo);

}