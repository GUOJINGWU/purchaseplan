/**  
 * @Title: BusinessAuthInterceptor.java
 * @Package com.youzhicai.purchaseplan.web
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月20日 下午6:46:55
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.youzhicai.purchaseplan.handler.HandlerClasses;

/**
 * @ClassName: BusinessAuthInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月20日 下午6:46:55
 */
public class BusinessAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> clazz = handlerMethod.getBeanType();

        if (HandlerClasses.BUSINESS_CLASSES.contains(clazz)) {
            // web层不允许REST访问API
            System.out.println("web层不允许REST访问API!");
//            throw new RuntimeException("web层不允许REST访问API!");
            return false;
        }

        return true;
    }

}
