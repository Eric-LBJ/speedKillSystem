package com.aikeeper.speed.kill.system.utils;

import java.util.Random;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 19:32
 * @Version V1.0
 **/
public class KeyFactory {

    public static final String strLetter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String number = "123456789";
    private static final Integer defaultLength = 6;

    /**
     * 获取随机数字字符集
     *
     * @param length
     * @return
     */
    public static String genRandomString(Integer length) {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (Integer i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

    /**
     * 获取随机数字
     *
     * @param length
     * @return
     */
    public static String genRandomNumberNoZore(Integer length) {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (Integer i = 0; i < length; i++) {
            int num = random.nextInt(9);
            buf.append(number.charAt(num));
        }
        return buf.toString();
    }


    /**
     * 获取token
     *
     * @param length
     * @return
     */
    public static String genLoginToken(Integer length) {
        String randomString = genRandomString(25) + "000";
        return randomString;
    }

    /**
     * 时间戳 + 随机数字
     *
     * @return
     */
    public static String genDefaultSerialNo() {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (Integer i = 0; i < defaultLength; i++) {
            int num = random.nextInt(9);
            buf.append(number.charAt(num));
        }
        return (System.currentTimeMillis() + buf.toString());
    }

    /**
     * 随机字符串
     *
     * @param length
     * @return
     */
    public static String genRandomStringLetter(Integer length) {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (Integer i = 0; i < length; i++) {
            int num = random.nextInt(52);
            buf.append(strLetter.charAt(num));
        }
        return buf.toString();
    }

}
