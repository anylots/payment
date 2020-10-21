package com.frame.tcctransaction.common.util;

/**
 * 事务订单记录状态枚举
 *
 * @author anylots
 * @version $Id: TransactionStatusEnum.java, v 0.1 2020年10月18日 17:48 anylots Exp $
 */
public enum OrderStatusEnum {

    //init
    INIT("INIT", "init"),

    //complete
    COMPLETE("COMPLETE", "complete");

    private String code;

    private String description;

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }


    OrderStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}