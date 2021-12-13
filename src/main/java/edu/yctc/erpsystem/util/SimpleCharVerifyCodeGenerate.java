package edu.yctc.erpsystem.util;


import edu.yctc.erpsystem.vo.VerifyCodeVO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 简单字符验证码生成
 *
 * @author lqp
 */
public class SimpleCharVerifyCodeGenerate {

    /**
     * 字体
     */
    private static final String[] FONT_TYPES = { "\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66" };

    /**
     * 验证码长度
     */
    private static final int CODE_LENGTH = 4;

    /**
     * 干扰线条数量
     */
    private static final int INTERFERENCE_LINES_NUMBER = 8;

    /**
     * 设置背景颜色及大小，干扰线
     *
     * @param graphics 画图板
     * @param width 宽度
     * @param height 高度
     */
    private static void fillBackground(Graphics graphics, int width, int height) {
        // 填充背景
        graphics.setColor(Color.WHITE);

        // 设置矩形坐标x y 为0
        graphics.fillRect(0, 0, width, height);

        // 加入干扰线条
        for (int i = 0; i < INTERFERENCE_LINES_NUMBER; ++i) {
            // 设置随机颜色算法参数
            graphics.setColor(RandomUtils.randomColor(40, 150));
            Random random = new Random();
            graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
    }

    /**
     * 生成随机字符
     *
     * @param width 图片宽度
     * @param height 图片高度
     * @param os 输出流
     * @return 随机字符
     * @throws IOException 文件异常
     */
    private static String generate(int width, int height, OutputStream os) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        fillBackground(graphics, width, height);
        String randomStr = RandomUtils.randomString(CODE_LENGTH);
        createCharacter(graphics, randomStr);
        graphics.dispose();
        // 设置JPEG格式
        ImageIO.write(image, "JPEG", os);
        return randomStr;
    }

    /**
     * 验证码生成
     *
     * @param width 图片宽度
     * @param height 图片高度
     * @return 验证码视图
     */
    public static VerifyCodeVO generate(int width, int height) {
        VerifyCodeVO verifyCode;
        try (
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        ) {
            String code = generate(width, height, byteArrayOutputStream);
            verifyCode = new VerifyCodeVO();
            verifyCode.setCode(code);
            verifyCode.setImgBytes(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            verifyCode = null;
        }
        return verifyCode;
    }

    /**
     * 设置字符颜色大小
     *
     * @param graphics 画图板
     * @param randomStr 随机字符串
     */
    private static void createCharacter(Graphics graphics, String randomStr) {
        char[] charArray = randomStr.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            // 设置RGB颜色算法参数
            graphics.setColor(new Color(50 + RandomUtils.nextInt(100), 50 + RandomUtils.nextInt(100), 50 + RandomUtils.nextInt(100)));
            // 设置字体大小，类型
            graphics.setFont(new Font(FONT_TYPES[RandomUtils.nextInt(FONT_TYPES.length)], Font.BOLD, 26));
            // 设置x y 坐标
            graphics.drawString(String.valueOf(charArray[i]), 15 * i + 5, 19 + RandomUtils.nextInt(8));
        }
    }

}
