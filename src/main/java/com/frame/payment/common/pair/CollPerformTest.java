package com.frame.payment.common.pair;

import java.util.*;

/**
 * @author anylots
 * @version $Id: CollPerformTest.java, v 0.1 2020年10月31日 11:18 anylots Exp $
 */
public class CollPerformTest {

    public static void main(String[] args) {

        List<String> keyList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            keyList.add(UUID.randomUUID().toString());
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            linearAddressMapTest(keyList);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("LinearAddressMap program time consuming:" + (endTime - startTime) + "ms");
    }

    private static void linearAddressMapTest(List<String> keyList) {
        LinearAddressMap<String, Object> linearAddressMap = new LinearAddressMap(16);
        for (String key : keyList) {
            linearAddressMap.put(key, key);
        }

        for (String key : keyList) {
            Object testValue = linearAddressMap.get(key);
        }
        linearAddressMap = null;
    }

    private static void hashMapTest(List<String> keyList) {
        Map<String, Object> hashMap = new HashMap();

        for (String key : keyList) {
            hashMap.put(key, key);
        }

        for (String key : keyList) {
            Object testValue = hashMap.get(key);
        }
        hashMap = null;
    }
}