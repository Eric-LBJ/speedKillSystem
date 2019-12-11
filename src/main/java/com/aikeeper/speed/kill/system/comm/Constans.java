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
     * 设置页面缓存过期时间一分钟
     */
    public static final Integer HTML_PAGE_EXPIRE = 60;
    /**
     * cookie名称
     */
    public static final String COOKIE_NAME_TOKEN = "token";
    /**
     * 默认订单状态
     */
    public static final Byte DEFAULT_STATUS = (byte) 0;
    /**
     * 默认收货地址编号
     */
    public static final Long DEFAULT_DELIVERY_ADDRESS_ID = 10969876987L;
    /**
     * 默认通道
     */
    public static final Byte DEFAULT_ORDER_CHANNEL = (byte) 0;
    /**
     * 默认商品数量
     */
    public static final Integer DEFAULT_GOODS_COUNT = 2;
    /**
     * MQ
     */
    public final static String QUEUE_NAME = "queue";
    /**
     * MQ Topic Queue
     */
    public final static String TOPIC_QUEUE_FIRST = "topic_queue_1";
    /**
     * MQ Topic Queue
     */
    public final static String TOPIC_QUEUE_SECOND= "topic_queue_2";
    /**
     * MQ topic Exchange
     */
    public final static String TOPIC_EXCHANGE= "topicExchange";
    /**
     * Topic Key
     */
    public final static String TOPIC_KEY_FIRST= "topic.key1";
    /**
     * Topic Key '#'通配符代表一个或多个任意字符
     */
    public final static String TOPIC_KEY_SECOND= "topic.#";
    /**
     * MQ Fanout Exchange
     */
    public final static String FANOUT_EXCHANGE= "fanoutExchange";
    /**
     * MQ Headers Exchange
     */
    public final static String HEADERS_EXCHANGE= "headersExchange";
    /**
     * MQ Headers Queue
     */
    public final static String HEADERS_QUEUE = "headers_queue";
}
