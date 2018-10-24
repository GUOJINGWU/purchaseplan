/**  
 * @Title: IOHelper.java
 * @Package com.youzhicai.purchaseplan.common.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年9月28日 下午3:44:02
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

/**
 * @ClassName: IOHelper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年9月28日 下午3:44:02
 */
public final class IOHelper {

    /*
     * 关闭输出流
     */
    public static void closeOutput(final OutputStream ops) {
        if (ops != null) {
            try {
                ops.flush();
                ops.close();
            } catch (final IOException ignore) {
            }
        }
    }

    /*
     * 关闭输入流
     */
    public static void closeInput(final InputStream ips) {
        if (ips != null) {
            try {
                ips.close();
            } catch (final IOException ignore) {
            }
        }
    }

    /*
     * 关闭读取流
     */
    public static void closeReader(final Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (final IOException ignore) {
            }
        }
    }

    /*
     * 拷贝流
     */
    public static void copyStream(final InputStream ips, final ByteArrayOutputStream ops) {
        final byte[] buf = new byte[10];
        int len;
        try {
            while ((len = ips.read(buf)) != -1) {
                ops.write(buf, 0, len);
            }
        } catch (final IOException ignore) {
            ignore.printStackTrace();
        }
    }

    private IOHelper() {
        throw new AssertionError("Uninstantiable class");
    }

}
