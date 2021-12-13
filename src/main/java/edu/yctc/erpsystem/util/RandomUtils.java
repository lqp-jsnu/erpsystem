package edu.yctc.erpsystem.util;

import edu.yctc.erpsystem.constant.ConstantHolder;

import java.awt.*;
import java.util.Random;

/**
 * 随机数生成
 *
 * @author xiaotao
 */
public class RandomUtils extends org.apache.commons.lang3.RandomUtils {

    private static final char[] CODE_SEQ = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static final char[] NUMBER_ARRAY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private static Random random = new Random();

    /**
     * 生成随机字符串
     *
     * @param length 字符串长度
     * @return 生成的字符串
     */
    static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(String.valueOf(CODE_SEQ[random.nextInt(CODE_SEQ.length)]));
        }
        return sb.toString();
    }

    /**
     * 生成随机字符串（含数字）
     *
     * @param length 字符串长度
     * @return 生成的字符串
     */
    static String randomNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(String.valueOf(NUMBER_ARRAY[random.nextInt(NUMBER_ARRAY.length)]));
        }
        return sb.toString();
    }

    /**
     *  生成随机颜色
     *
     * @param fontColor 字体颜色
     * @param backgroundColor 背景颜色
     * @return 颜色
     */
    static Color randomColor(int fontColor, int backgroundColor) {
        int f = fontColor;
        int b = backgroundColor;
        Random random = new Random();
        if (f > ConstantHolder.MAX_COLOR_CODE) {
            f = ConstantHolder.MAX_COLOR_CODE;
        }
        if (b > ConstantHolder.MAX_COLOR_CODE) {
            b = ConstantHolder.MAX_COLOR_CODE;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
    }

    /**
     * 生成整数
     *
     * @param bound 整数范围
     * @return 整数
     */
    static int nextInt(int bound) {
        return random.nextInt(bound);
    }

}
