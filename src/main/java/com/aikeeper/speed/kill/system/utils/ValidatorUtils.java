package com.aikeeper.speed.kill.system.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 14:33
 * @Version V1.0
 **/
public class ValidatorUtils {

    /**正则表达式*/
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static Boolean isPhone(String phone){
        if(StringUtils.isEmpty(phone)) {
            return Boolean.FALSE;
        }
        Matcher m = mobile_pattern.matcher(phone);
        return m.matches();
    }

}
