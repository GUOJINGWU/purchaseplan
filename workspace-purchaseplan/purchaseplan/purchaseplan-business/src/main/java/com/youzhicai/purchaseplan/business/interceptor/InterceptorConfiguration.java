/**  
 * @Title: InterceptorConfiguration.java
 * @Package com.youzhicai.purchaseplan.business.interceptor
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月20日 下午6:25:35
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.business.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName: InterceptorConfiguration
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月20日 下午6:25:35
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new ParamsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new DataTransferInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
