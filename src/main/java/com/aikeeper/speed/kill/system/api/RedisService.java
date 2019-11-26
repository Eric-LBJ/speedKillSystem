package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.comm.keyclass.KeyPrefix;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/24 17:10
 * @Version V1.0
 **/
public interface RedisService {

    /**
     * 获取值
     *
     * @param prefix
     * @param redisKey
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(KeyPrefix prefix, String redisKey, Class<T> clazz);

    /**
     * 设置对象
     *
     * @param prefix
     * @param redisKey
     * @param value
     * @param <T>
     * @return
     */
    <T> Boolean set(KeyPrefix prefix, String redisKey, T value);

    /**
     * redisKey是否已经存在
     *
     * @param prefix
     * @param redisKey
     * @return
     */
    Boolean exists(KeyPrefix prefix, String redisKey);

    /**
     * 自增
     *
     * @param prefix
     * @param redisKey
     * @param <T>
     * @return
     */
    <T> Long incr(KeyPrefix prefix, String redisKey);

    /**
     * 自减
     *
     * @param prefix
     * @param redisKey
     * @param <T>
     * @return
     */
    <T> Long decr(KeyPrefix prefix, String redisKey);

}
