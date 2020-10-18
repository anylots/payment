package com.frame.payment.common.util;

/**
 * 事务状态枚举
 *
 * @author anylots
 * @version $Id: TransactionStatusEnum.java, v 0.1 2020年10月18日 17:48 anylots Exp $
 */
public enum TransactionStatusEnum {

    STATUS_PREPARE("PREPARE", "status_prepare"),

    STATUS_COMMITTED("COMMIT", "status_committed"),

    STATUS_ROLLED_BACK("ROLLBACK", "status_rolled_back"),

    STATUS_UNKNOWN("UNKNOWN", "unknown");


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


    TransactionStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}