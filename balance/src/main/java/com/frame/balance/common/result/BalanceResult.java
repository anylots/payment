package com.frame.balance.common.result;

import java.util.Map;

/**
 * @author anylots
 * @version $Id: BalanceResult.java, v 0.1 2020年10月17日 13:56 anylots Exp $
 */
public class BalanceResult {

    private boolean success;

    private Map<String, String> extendInfo;

    /**
     * Getter method for property <tt>success</tt>.
     *
     * @return property value of success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Setter method for property <tt>success</tt>.
     *
     * @param success value to be assigned to property success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Getter method for property <tt>extendInfo</tt>.
     *
     * @return property value of extendInfo
     */
    public Map<String, String> getExtendInfo() {
        return extendInfo;
    }

    /**
     * Setter method for property <tt>extendInfo</tt>.
     *
     * @param extendInfo value to be assigned to property extendInfo
     */
    public void setExtendInfo(Map<String, String> extendInfo) {
        this.extendInfo = extendInfo;
    }
}