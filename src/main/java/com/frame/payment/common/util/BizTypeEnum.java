package com.frame.payment.common.util;

/**
 * enumeration of biz type
 *
 * @author anylots
 * @version $Id: BizTypeEnum.java, v 0.1 2020年10月25日 11:58 anylots Exp $
 */
public enum BizTypeEnum {

    //payment
    PAYMENT("PAYMENT", "payment");

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


    BizTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}