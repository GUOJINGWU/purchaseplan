/**  
 * @Title: RandomUtil.java
 * @Package com.yzc.utils
 * @Description: 随机数工具类
 * @author XieXianpeng
 * @date 2018年5月3日 下午1:48:57
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.web.util;

import java.util.Random;

/**
 * @ClassName: RandomUtil
 * @Description: 随机数工具类
 * @author XieXianpeng
 * @date 2018年5月3日 下午1:48:57
 */
public final class RandomUtil {

    private static final Random RANDOM = new Random();

    /**
     * @Title: randomStrByLength
     * @Description: 指定位数的随机数字字符串
     * @param length 长度
     * @return String
     */
    public static String randomStrByLength(int length) {
        return randomStr(toplimit(length));
    }

    /**
     * @Title: randomInt
     * @Description: 随机非负整数
     * @param toplimit 上限值(左闭右开)
     * @return int
     */
    public static int randomInt(int toplimit) {
        return RANDOM.nextInt(toplimit);
    }

    /*
     * 随机非负整数字符串
     */
    private static String randomStr(int toplimit) {
        return supplement(toplimit, randomInt(toplimit)).toString();
    }

    /*
     * 补全空位置
     */
    private static StringBuffer supplement(int toplimit, int randomInt) {
        StringBuffer buffer = new StringBuffer();
        int tldc = digitCapacity(toplimit);
        int ridc = digitCapacity(randomInt);
        for (int i = 1; i < tldc - ridc; i++) {
            buffer.append(0);
        }
        return buffer.append(randomInt);
    }

    /*
     * 数字-->位数
     */
    private static int digitCapacity(int digit) {
        int dc = 0;
        if (digit == 0) {
            ++dc;
        } else if (digit > 0) {
            int num = digit;
            while (num > 0) {
                num = num / 10;
                ++dc;
            }
        }
        return dc;
    }

    /*
     * 位数-->随机数上限
     */
    private static int toplimit(int digitCapacity) {
        int tl = 1;
        while (digitCapacity > 0) {
            tl *= 10;
            --digitCapacity;
        }
        return tl;
    }
}
