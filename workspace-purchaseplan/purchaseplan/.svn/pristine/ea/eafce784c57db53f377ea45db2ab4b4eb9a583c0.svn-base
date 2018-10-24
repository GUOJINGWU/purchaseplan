/**  
 * @Title: Strings.java
 * @Package com.youzhicai.commons.log.util
 * @Description: Strings
 * @author XieXianpeng
 * @date 2018-5-22 13:48:56
 * @version V0.0.1
 */
package com.youzhicai.purchaseplan.web.util;

/**
 * @ClassName: Strings
 * @Description: Strings
 * @author XieXianpeng
 * @date 2018-5-22 13:48:56
 */
public class Strings {

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !Strings.isBlank(str);
    }

    public static Long str2Long(String str) {
        Long l = null;
        try {
            l = Long.parseLong(str);
        } catch (Exception e) {
        }
        return l;
    }

}
