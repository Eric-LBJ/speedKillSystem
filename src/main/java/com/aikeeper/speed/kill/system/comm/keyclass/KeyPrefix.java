package com.aikeeper.speed.kill.system.comm.keyclass;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 10:13
 * @Version V1.0
 **/
public interface KeyPrefix {

    /**
     * 获取过期时间
     *
     * @return
     */
    Integer expireSeconds();

    /**
     * 获取redisKey前缀
     *
     * @return
     */
    String getPrefix();

}
