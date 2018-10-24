/**  
 * @Title: Params.java
 * @Package com.youzhicai.purchaseplan.common.constant
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月27日 下午2:53:12
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.common.constant;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.youzhicai.purchaseplan.common.util.ConfigUtil;

/**
 * @ClassName: Params
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月27日 下午2:53:12
 */
public class Params {

    public final static String PARAMS = Params.class.getName() + ".Params";

//    public final static <T> T toObj(HttpServletRequest request, Class<T> clazz) {
//        String params = (String) request.getAttribute(PARAMS);
//        T t = JSON.parseObject(params, clazz);
//        return t;
//    }

    public final static <T> T toObj(HttpServletRequest request, T t) {
        String value = ConfigUtil.getValue("serviceUsing");
        if (StringUtils.isNotBlank(value) && "true".equals(value)) {
            String params = (String) request.getAttribute(PARAMS);
            t = JSON.parseObject(params, t.getClass());
        }
        return t;
    }

}
