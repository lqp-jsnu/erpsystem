package edu.yctc.erpsystem.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * 文件操作工具类
 *
 * @author xiaotao
 */
public class FileUtils {

    /**
     * 判断文件夹是否存在 不存在则创建文件夹
     *
     * @param dirPath 文件夹路径
     * @return 是否成功
     */
    static public Boolean isDirExists(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return dir.mkdirs();
        }
        return true;
    }

    /**
     * 读取图片
     *
     * @param img 图片
     * @return 图片字节
     */
    static public byte[] fileToByte(File img) {
        byte[] bytes = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "png", outputStream);
            bytes = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 删除文件
     *
     * @param dirPath 文件夹路径
     * @param name 文件名称
     * @return 是否成功
     */
    static public Boolean deleteFile(String dirPath, String name) {
        File file = new File(dirPath + name);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */
    static public void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (File item : files) {
                    deleteDir(item.getAbsolutePath());
                }
                file.delete();
            }
        }
    }

}
