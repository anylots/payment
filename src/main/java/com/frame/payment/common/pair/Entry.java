package com.frame.payment.common.pair;

/**
 * @author anylots
 * @version $Id: Entry.java, v 0.1 2020年10月31日 11:17 anylots Exp $
 */
public class Entry {

    /*** key*/
    private String key;
    /*** value*/
    private Object value;

    public Entry(String k, Object v) {
        this.key = k;
        this.value = v;
    }

    /*** Getter method for property <tt>key</tt>.** @return property value of key*/
    public String getKey() {
        return key;
    }

    /*** Setter method for property <tt>key</tt>.** @param key value to be assigned to property key*/
    public void setKey(String key) {
        this.key = key;
    }

    /*** Getter method for property <tt>value</tt>.** @return property value of value*/
    public Object getValue() {
        return value;
    }

    /*** Setter method for property <tt>value</tt>.** @param value value to be assigned to property value*/
    public void setValue(Object value) {
        this.value = value;
    }
}