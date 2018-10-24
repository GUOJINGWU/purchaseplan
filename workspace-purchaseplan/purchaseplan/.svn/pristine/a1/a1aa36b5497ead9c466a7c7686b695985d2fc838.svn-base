/**  
 * @Title: POUtil.java
 * @Package com.youzhicai.purchaseplan.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月18日 下午6:37:04
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.common.util;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * @ClassName: POUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月18日 下午6:37:04
 */
public class POUtil {

    public static ModelMapper modelMapper;

    static {
        synchronized (POUtil.class) {
            if (modelMapper == null) {
                modelMapper = new ModelMapper();
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            }
        }
    }

    public static <S, D> D convert(S source, Class<D> clazz) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, clazz);
    }

    public static <S, D> List<D> convert(List<S> source, Class<D> clazz, Type type) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, type);
    }

}
