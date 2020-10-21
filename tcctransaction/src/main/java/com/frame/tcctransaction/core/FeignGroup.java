package com.frame.tcctransaction.core;

import com.frame.tcctransaction.client.BalanceServiceClient;
import com.frame.tcctransaction.client.CouponServiceClient;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * feign group
 *
 * @author anylots
 * @version $Id: FeignGroup.java, v 0.1 2020年10月21日 20:41 anylots Exp $
 */
public class FeignGroup {

    public final static Map<String, Set<Class>> feignGroup = new HashMap<>();

    static {
        Set<Class> feignSets = new HashSet<>();
        feignSets.add(BalanceServiceClient.class);
        feignSets.add(CouponServiceClient.class);
        feignGroup.put("payment", feignSets);
    }
}