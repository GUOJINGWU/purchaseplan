/**  
 * @Title: InitProperties.java
 * @Package com.youzhicai.purchaseplan.common.config
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月20日 下午7:07:18
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.common.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youzhicai.purchaseplan.handler.BusinessAPI;
import com.youzhicai.purchaseplan.handler.HandlerClasses;
import com.youzhicai.purchaseplan.handler.UnLogin;

/**
 * @ClassName: InitProperties
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月20日 下午7:07:18
 */
@Configuration
public class InitProperties implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    /*
     * @Title: setApplicationContext
     * 
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * 
     * @param applicationContext
     * 
     * @throws BeansException
     * 
     * @see
     * org.springframework.context.ApplicationContextAware#setApplicationContext
     * (org.springframework.context. ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == applicationContext) {
            System.err.println("applicationContext must not null");
        } else {
            this.applicationContext = applicationContext;
        }
    }

    /*
     * @Title: afterPropertiesSet
     * 
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * 
     * @throws Exception
     * 
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        initHandlerClasses();
        initHandlerMethods();
    }

    /* 类注解命中 */
    private <A extends Annotation> boolean hitClassAnnotation(Class<?> clazz, Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(clazz, annotationType) != null;
    }

    /* 方法注解命中 */
    private <A extends Annotation> boolean hitMethodAnnotation(Method method, Class<A> annotationType) {
        return method.getAnnotation(annotationType) != null;
    }

    // 初始化类对象参数
    private void initHandlerClasses() {
        String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
        for (String beanName : beanNames) {
            Class<?> clazz = applicationContext.getType(beanName);
            initRequestMappingClasses(clazz);
            initBusinessClasses(clazz);
        }
    }

    /* REQUESTMAPPING_CLASSES */
    private void initRequestMappingClasses(Class<?> clazz) {
        if (hitClassAnnotation(clazz, RequestMapping.class)) {
            HandlerClasses.REQUESTMAPPING_CLASSES.add(clazz);
        }
    }

    /* BUSINESS_CLASSES */
    private void initBusinessClasses(Class<?> clazz) {
        if (hitClassAnnotation(clazz, BusinessAPI.class)) {
            HandlerClasses.BUSINESS_CLASSES.add(clazz);
        }
    }

    // 初始化控制类方法
    private void initHandlerMethods() {
        for (Class<?> clazz : HandlerClasses.REQUESTMAPPING_CLASSES) {
            for (Method method : clazz.getMethods()) {
                initWebMethodsUnLogin(method);
            }
        }
    }

    /* WEB_METHODS_UNLOGIN */
    private void initWebMethodsUnLogin(Method method) {
        if (hitMethodAnnotation(method, UnLogin.class)) {
            HandlerClasses.WEB_METHODS_UNLOGIN.add(method);
        }
    }

}
