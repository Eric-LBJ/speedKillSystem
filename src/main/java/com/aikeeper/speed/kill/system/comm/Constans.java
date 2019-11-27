package com.aikeeper.speed.kill.system.comm;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/22 17:28
 * @Version V1.0
 **/
public class Constans {

    /**
     * 成功时返回码
     */
    public static final Integer SUCCESS_CODE = 200;
    /**
     * 成功时返回信息
     */
    public static final String SUCCESS_MESSAGE = "success";
    /**
     * 服务端异常返回码
     */
    public static final Integer SERVER_ERROR_CODE = 500100;
    /**
     * 服务端异常返回信息
     */
    public static final String SERVER_ERROR_MESSAGE = "服务端异常";
    /**
     * 默认过期时间
     */
    public static final Integer DEFAULT_EXPIRE_SECONDS = 0;
    /**
     * 默认分隔符
     */
    public static final String DEFAULT_SEPARATOR = "_";
    /**
     * redis set操作成功返回值
     */
    public static final String REPLAY_OF_OK = "OK";
    /**
     * 设置token过期时间两天
     */
    public static final Integer TOKEN_EXPIRE = 3600 * 24 * 2;
    /**
     * cookie名称
     */
    public static final String COOKIE_NAME_TOKEN = "token";
}
