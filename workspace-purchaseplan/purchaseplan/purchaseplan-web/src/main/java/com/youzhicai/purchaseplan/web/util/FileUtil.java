/**  
 * @Title: FileUtil.java
 * @Package com.youzhicai.materialstore.web.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author XieXianpeng
 * @date 2018年10月13日 下午7:47:18
 * @version V1.0  
 */
package com.youzhicai.purchaseplan.web.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @ClassName: FileUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XieXianpeng
 * @date 2018年10月13日 下午7:47:18
 */
public class FileUtil {
    public static void main(String[] args) {
        String inpath = "D:\\FileTest\\supplypur-standard\\1.rar";
        String oString = "D:\\FileTest\\supplypur-standard\\1_copy.rar";
        Boolean copyFilesValue = copyFilesValue(inpath, oString);

    }

    /**
     * 删除文件，可以是文件或文件夹
     * 
     * @param fileName :要删除的文件,或者目录
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 
     * @param fileName :要删除的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir :要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取文件扩展名
     * 
     * @param filename
     * @return
     * @author douqr 2018-02-12
     */
    public static String getFileNameSuffix(String filename) {

        if ((filename != null) && (filename.length() > 0)) {

            if (filename.indexOf(".") == -1) {
                return "";
            }

            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(dot);
            }
        }
        return filename;
    }

    /**
     * 将内容保存到指定路径下
     * 
     * @author xia.nan
     * @date 2018-07-27
     */
    public static Boolean saveFilesValue(String fileName, String fileValue, String filePath) {
        File file = null;
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        try {
            String path = filePath + File.separator + fileName;
            file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            outSTr = new FileOutputStream(new File(path));
            Buff = new BufferedOutputStream(outSTr);
            Buff.write(fileValue.getBytes());
            Buff.flush();
            Buff.close();
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        } finally {
            try {
                Buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件中的内容
     * 
     * @author xia.nan
     * @date 2018-07-27
     */
    public static String readFileValue(String fileName, String filePath) {
        BufferedReader br = null;
        try {
            String path = filePath + File.separator + fileName;
            br = new BufferedReader(new FileReader(path));
            StringBuffer out = new StringBuffer();
            String contentLine = br.readLine();
            while (contentLine != null) {
                out.append(contentLine + "\n");
                contentLine = br.readLine();
            }
            return out.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String();
    }

    /**
     * 文件拷贝
     * 
     * @author xia.nan
     * @date 2018-08-08
     */
    @SuppressWarnings("resource")
    public static Boolean copyFilesValue(String filePathOld, String filePathNew) {
        FileChannel input = null;
        FileChannel output = null;
        try {
            input = new FileInputStream(filePathOld).getChannel();
            output = new FileOutputStream(filePathNew).getChannel();
            output.transferFrom(input, 0, input.size());
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Boolean.FALSE;
    }
}
