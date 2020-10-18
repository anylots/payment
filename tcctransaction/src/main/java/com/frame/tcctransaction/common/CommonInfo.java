package com.frame.tcctransaction.common;

/**
 * common info of request
 *
 * @author anylots
 * @version $Id: CommonIInfo.java, v 0.1 2020年10月17日 14:11 anylots Exp $
 */
public class CommonInfo {

    /**
     * operation type,such as:try,confirm,cancel
     */
    private String stage = "PREPARE";

    /**
     * 订单id，幂等id，订单唯一识别id
     */
    private String orderId;

    /**
     * Getter method for property <tt>stage</tt>.
     *
     * @return property value of stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * Setter method for property <tt>stage</tt>.
     *
     * @param stage value to be assigned to property stage
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * Getter method for property <tt>orderId</tt>.
     *
     * @return property value of orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Setter method for property <tt>orderId</tt>.
     *
     * @param orderId value to be assigned to property orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * is transaction on prepare stage
     *
     * @return
     */
    public boolean isOnPrepareStage() {
        return "PREPARE".equals(stage);
    }
}