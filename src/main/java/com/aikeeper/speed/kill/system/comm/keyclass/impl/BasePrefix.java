package com.aikeeper.speed.kill.system.comm.keyclass.impl;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.KeyPrefix;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 10:16
 * @Version V1.0
 **/
public abstract class BasePrefix implements KeyPrefix {

    /**
     * 过期时间
     */
    private Integer expireSeconds;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 默认0代表用不过期
     *
     * @param prefix
     */
    public BasePrefix(String prefix) {
        this(Constans.DEFAULT_EXPIRE_SECONDS, prefix);
    }

    public BasePrefix(Integer expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public Integer expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return getClass().getSimpleName().toUpperCase() + Constans.DEFAULT_SEPARATOR + prefix.toUpperCase() + Constans.DEFAULT_SEPARATOR;
    }

}
