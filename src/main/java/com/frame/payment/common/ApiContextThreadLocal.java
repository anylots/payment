package com.frame.payment.common;

/**
 * @author anylots
 * @version $Id: ApiContext.java, v 0.1 2020年10月25日 11:52 anylots Exp $
 */
public class ApiContextThreadLocal {

    /**
     * api context
     */
    private static ThreadLocal<ApiContext> API_CONTEXT = new ThreadLocal<>();

    /**
     * * Sets the current thread's copy of this thread-local variable
     *
     * @param apiContext
     */
    public static void set(ApiContext apiContext) {
        API_CONTEXT.set(apiContext);
    }

    public static ApiContext get() {
        return API_CONTEXT.get();
    }

    public static void remove() {
        API_CONTEXT.remove();
    }
}