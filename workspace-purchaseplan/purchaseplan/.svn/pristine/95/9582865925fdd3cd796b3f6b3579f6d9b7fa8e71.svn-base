/**  
 * @Title: ParamsInterceptor.java
 * @Package com.youzhicai.purchaseplan.business.interceptor
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月27日 上午11:34:01
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.business.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youzhicai.purchaseplan.common.constant.Params;
import com.youzhicai.purchaseplan.common.util.IOHelper;

/**
 * @ClassName: ParamsInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月27日 上午11:34:01
 */
public class ParamsInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(ParamsInterceptor.class);

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

        final boolean des = getDesHeader(request);
        response.setHeader("des", String.valueOf(des));

        final boolean stream = checkStream(request);
        final String method = request.getMethod();

        String params = null;
        if (method.equalsIgnoreCase("POST")) {
            final ByteArrayOutputStream output = copyStream(request);
            params = streamCollector(output, des);
            if (output != null) {
                output.close();
            }
        } else if (!stream || method.equalsIgnoreCase("GET")) {
            params = commonCollector(request, null);
        }

        request.setAttribute(Params.PARAMS, params);

        return true;
    }

    /*
     * 是否流传输
     */
    private boolean checkStream(final HttpServletRequest request) {
        return MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType());
    }

    /*
     * 是否加密
     */
    private boolean getDesHeader(final HttpServletRequest request) {
        return Boolean.parseBoolean(request.getHeader("des"));
    }

    /*
     * 复制流
     */
    private ByteArrayOutputStream copyStream(final HttpServletRequest request) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            final InputStream is = request.getInputStream();
            IOHelper.copyStream(is, baos);
            if (is != null) {
                is.close();
            }
        } catch (final IOException e) {
            logger.error("copy reqest inputstream failture.", e);
        }
        return baos;
    }

    /*
     * 收集流参数
     */
    private String streamCollector(final ByteArrayOutputStream t, final Boolean v) {
        if (t == null) {
            return null;
        }
        String body = null;
        try {
            body = new String(t.toByteArray(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("body:" + body);
        return body;
    }

    /*
     * 收集普通参数
     */
    private String commonCollector(final HttpServletRequest request, final Void v) {
        final Map<String, Object> params = new HashMap<String, Object>();
        final Enumeration<String> paramNames = request.getParameterNames();
        for (; paramNames.hasMoreElements();) {
            final String name = paramNames.nextElement();
            final String value = request.getParameter(name);
            params.put(name, value);
        }
        if (params.isEmpty()) {
            return null;
        }
        final ObjectMapper mapper = new ObjectMapper();
        String value = null;
        try {
            value = mapper.writeValueAsString(params);
        } catch (final IOException ignore) {
            logger.error("request params transfer to json failture.", ignore);
        }
        return value;
    }

}