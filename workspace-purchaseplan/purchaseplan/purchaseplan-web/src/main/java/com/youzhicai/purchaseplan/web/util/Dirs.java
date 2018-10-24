/**  
 * @Title: Dirs.java
 * @Package com.youzhicai.commons.log.util
 * @Description: mkdirs
 * @author XieXianpeng
 * @date 2018-5-17 12:03:39
 * @version V0.0.1  
 */
package com.youzhicai.purchaseplan.web.util;

import java.io.File;

/**
 * @ClassName: Dirs
 * @Description: mkdirs
 * @author XieXianpeng
 * @date 2018-5-17 12:03:39
 */
public class Dirs {

    /*
     * mkdirs
     */
    public static boolean mkdirs(String filePath) {
        boolean mk = false;
        String dirs = dirs(filePath);
        if (Strings.isNotBlank(dirs)) {
            File file = new File(dirs);
            if (!file.exists()) {
                mk = file.mkdirs();
            }
        }
        return mk;
    }

    /*
     * dirs
     */
    private static String dirs(String filePath) {
        String dirs = dirs(filePath, "/");
        if (Strings.isBlank(dirs)) {
            dirs = dirs(filePath, "\\");
        }
        return dirs;
    }

    /*
     * dirsPath
     */
    private static String dirs(String filePath, String regex) {
        String parentPath = null;
        int lastIdx = filePath.lastIndexOf(regex);
        if (lastIdx > 0) {
            parentPath = filePath.substring(0, lastIdx);
        }
        return parentPath;
    }

}
