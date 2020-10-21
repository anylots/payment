package com.frame.tcctransaction.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logger util
 *
 * @author anylots
 * @version $Id: LoggerUtil.java, v 0.1 2020年10月21日 20:07 anylots Exp $
 */
public class LoggerUtil {

    private final static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);


    /**
     * error log
     *
     * @param msg
     */
    public static void error(String msg) {
        if (logger.isErrorEnabled()) {
            logger.error(msg);
        }
    }

    /**
     * error log
     * @param msg
     * @param e
     */
    public static void error(String msg, Throwable e) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, e);
        }
    }

    /**
     * info log
     *
     * @param msg
     */
    public static void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

}