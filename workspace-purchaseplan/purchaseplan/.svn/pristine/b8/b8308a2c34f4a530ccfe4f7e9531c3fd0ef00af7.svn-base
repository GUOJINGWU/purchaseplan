/**  
 * @Title: DataTransferInterceptor.java
 * @Package com.youzhicai.purchaseplan.business.interceptor
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月27日 上午11:34:01
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.business.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @ClassName: DataTransferInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月27日 上午11:34:01
 */
public class DataTransferInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(DataTransferInterceptor.class);

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String userAgent = request.getHeader("user-agent");
        if (StringUtils.isNotBlank(userAgent) && userAgent.contains("Java/")) {
            request.setAttribute("transfer", false);
        }
        logger.debug("userAgent: " + userAgent);

        return true;
    }

}