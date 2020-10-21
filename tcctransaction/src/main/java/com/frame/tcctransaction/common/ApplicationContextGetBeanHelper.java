package com.frame.tcctransaction.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author anylots
 * @version $Id: ApplicationContextGetBeanHelper.java, v 0.1 2020年10月17日 17:44 anylots Exp $
 */
@Component
public class ApplicationContextGetBeanHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object getBean(Class clazz) throws BeansException, IllegalArgumentException {
        if (clazz == null) {
            throw new IllegalArgumentException("class为空");
        }

        return applicationContext != null ? applicationContext.getBean(clazz) : null;
    }

}