package com.frame.payment.common;

import java.lang.annotation.*;

/**
 * @author anylots
 * @version $Id: TwoStages.java, v 0.1 2020年10月17日 16:04 anylots Exp $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TwoStages {

    String value() default "";

}