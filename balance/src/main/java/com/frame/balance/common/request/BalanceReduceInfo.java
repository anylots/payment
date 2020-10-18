package com.frame.balance.common.request;

import com.frame.tcctransaction.common.CommonInfo;

/**
 * @author anylots
 * @version $Id: BalanceManageRequest.java, v 0.1 2020年10月17日 13:48 anylots Exp $
 */
public class BalanceReduceInfo extends CommonInfo {

    /**
     * account No of user
     */
    private String accountNo;

    /**
     * amount of money
     */
    private double amount;

    /**
     * Getter method for property <tt>accountNo</tt>.
     *
     * @return property value of accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Setter method for property <tt>accountNo</tt>.
     *
     * @param accountNo value to be assigned to property accountNo
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * Getter method for property <tt>amount</tt>.
     *
     * @return property value of amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter method for property <tt>amount</tt>.
     *
     * @param amount value to be assigned to property amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

}