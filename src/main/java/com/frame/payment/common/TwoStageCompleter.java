package com.frame.payment.common;

import com.frame.payment.common.util.LoggerUtil;
import com.frame.tcctransaction.common.CommonInfo;

import java.lang.reflect.Method;

/**
 * 两阶段事务完成者
 *
 * @author anylots
 * @version $Id: TwoStageSync.java, v 0.1 2020年10月18日 20:14 anylots Exp $
 */
public class TwoStageCompleter {

    /**
     * name of the class 参与者类
     */
    private Class targetClass;

    /**
     * name of the class 参与者方法名
     */
    private String methodName;

    /**
     * 参与者请求参数
     */
    private CommonInfo commonInfo;


    /**
     * invoke after prepare
     *
     * @param stage
     */
    public void invokeAfterPrepare(String stage) {

        //设置参与者请求阶段
        commonInfo.setStage(stage);

        //调用参与者提交、回滚
        try {
            Method method = targetClass.getMethod(methodName, new Class[]{CommonInfo.class});
            method.invoke(ApplicationContextGetBeanHelper.getBean(targetClass), commonInfo);

        } catch (ReflectiveOperationException e) {
            LoggerUtil.error("tcc method invoke error", e);
            throw new RuntimeException("tcc method invoke error", e);
        }
        LoggerUtil.info("远程参与者事务提交/回滚完成");
    }

    /**
     * Getter method for property <tt>targetClass</tt>.
     *
     * @return property value of targetClass
     */
    public Class getTargetClass() {
        return targetClass;
    }

    /**
     * Setter method for property <tt>targetClass</tt>.
     *
     * @param targetClass value to be assigned to property targetClass
     */
    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Getter method for property <tt>methodName</tt>.
     *
     * @return property value of methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Setter method for property <tt>methodName</tt>.
     *
     * @param methodName value to be assigned to property methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Getter method for property <tt>commonInfo</tt>.
     *
     * @return property value of commonInfo
     */
    public CommonInfo getCommonInfo() {
        return commonInfo;
    }

    /**
     * Setter method for property <tt>commonInfo</tt>.
     *
     * @param commonInfo value to be assigned to property commonInfo
     */
    public void setCommonInfo(CommonInfo commonInfo) {
        this.commonInfo = commonInfo;
    }
}