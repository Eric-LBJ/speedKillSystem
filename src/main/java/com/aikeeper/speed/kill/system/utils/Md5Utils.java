package com.aikeeper.speed.kill.system.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 11:16
 * @Version V1.0
 **/
public class Md5Utils {

    private static String salt = "1a2b3c4d5e6f7g";

    /**
     * md5数据加密
     *
     * @param src 需要加密的数据
     * @return
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * form表单提交的密码经过固定的salt有MD5加密一次
     * 踩坑：encryptedData拼接的时候一定要加[""+],不然字符会转成数字相加减，
     * 例如这里的salt.charAt(0) + salt.charAt(5)如果不加[""+]结果为147，而加了结果为1c
     *
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String encryptedData = "" + salt.charAt(0) + salt.charAt(5) + inputPass + salt.charAt(3) + salt.charAt(8);
        System.out.println(encryptedData);
        System.out.println(salt);
        return md5(encryptedData);
    }

    /**
     * 将加密过一次的密码，再经过一次MD5加密
     *
     * @param formPass 加密过的密码
     * @param dbSalt   随机salt
     * @return
     */
    public static String formPassToDbPassword(String formPass, String dbSalt) {
        String encryptedData = "" + salt.charAt(0) + salt.charAt(5) + formPass + salt.charAt(3) + salt.charAt(8);
        return md5(encryptedData);
    }

    /**
     * 由表单提交的密码直接转换成数据库需要存储的密码
     *
     * @param inputPass
     * @param dbSalt
     * @return
     */
    public static String inputPassToDbPassword(String inputPass, String dbSalt) {
        String formPass = inputPassToFormPass(inputPass);
        return formPassToDbPassword(formPass, dbSalt);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDbPassword("123456", salt));
    }

}
