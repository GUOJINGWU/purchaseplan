/**  
 * @Title: DataTransferFilter.java
 * @Package com.youzhicai.purchaseplan.business.filter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月28日 下午3:29:02
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.business.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youzhicai.purchaseplan.business.transfer.DataTransfer;
import com.youzhicai.purchaseplan.business.transfer.TransferType;

/**
 * @ClassName: DataTransferFilter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月28日 下午3:29:02
 */
public class DataTransferFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(DataTransferFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ResponseWrapper wrapperResponse = new ResponseWrapper((HttpServletResponse) response);// 转换成代理类
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ObjectMapper mapper = new ObjectMapper();

        String ciphertext = null;
        try {
            filterChain.doFilter(request, wrapperResponse);// 放行请求

            byte[] content = wrapperResponse.getContent();// 返回值
            // 返回值是否有效
            if (content.length > 0) {
                // 处理数据
                String str = new String(content, "UTF-8");
                Object obj = mapper.readValue(str, Object.class);
                ciphertext = mapper.writeValueAsString(
                        transfer(httpRequest) ? new DataTransfer<Object>(TransferType.SUCCESS.getCode(), TransferType.SUCCESS.getMsg(), obj) : obj);
            } else {
                ciphertext = transfer(httpRequest)
                        ? mapper.writeValueAsString(new DataTransfer<Object>(TransferType.DATANULL.getCode(), TransferType.DATANULL.getMsg())) : null;
            }
        } catch (Exception e) {
            ciphertext = transfer(httpRequest)
                    ? mapper.writeValueAsString(new DataTransfer<Object>(TransferType.ERROR.getCode(), TransferType.ERROR.getMsg())) : null;
            logger.error(ciphertext, e);
        }
        if (StringUtils.isNotBlank(ciphertext)) {
            // 输出返回值
            ServletOutputStream out = response.getOutputStream();
            out.write(ciphertext.getBytes());
            out.flush();
        }
    }

    private boolean transfer(HttpServletRequest httpRequest) {
        Boolean dataTransfer = (Boolean) httpRequest.getAttribute("transfer");
        return dataTransfer == null || dataTransfer;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
