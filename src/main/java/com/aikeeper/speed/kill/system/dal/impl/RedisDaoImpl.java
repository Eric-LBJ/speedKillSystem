package com.aikeeper.speed.kill.system.dal.impl;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.KeyPrefix;
import com.aikeeper.speed.kill.system.dal.RedisDao;
import com.aikeeper.speed.kill.system.utils.ConvertUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/24 16:53
 * @Version V1.0
 **/
@Component
public class RedisDaoImpl implements RedisDao {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public <T> T get(KeyPrefix prefix, String redisKey, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            /**获取真正的key*/
            String realKey = prefix.getPrefix() + redisKey;
            String redisValue = jedis.get(realKey);
            T result = ConvertUtils.stringToBean(redisValue, clazz);
            return result;
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public <T> Boolean set(KeyPrefix prefix, String redisKey, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = ConvertUtils.beanToString(value);
            if (StringUtils.isEmpty(str)) {
                return false;
            }
            //生成真正的key
            String realKey = prefix.getPrefix() + redisKey;
            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {
                return jedis.set(realKey, str).equals(Constans.REPLAY_OF_OK) ? Boolean.TRUE : Boolean.FALSE;
            } else {
                return jedis.setex(realKey, seconds, str).equals(Constans.REPLAY_OF_OK) ? Boolean.TRUE : Boolean.FALSE;
            }
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public Boolean exists(KeyPrefix prefix, String redisKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            /**获取真正的key*/
            String realKey = prefix.getPrefix() + redisKey;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public <T> Long incr(KeyPrefix prefix, String redisKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            /**获取真正的key*/
            String realKey = prefix.getPrefix() + redisKey;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public <T> Long decr(KeyPrefix prefix, String redisKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            /**获取真正的key*/
            String realKey = prefix.getPrefix() + redisKey;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public <T> Long delete(KeyPrefix prefix, String redisKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            /**获取真正的key*/
            String realKey = prefix.getPrefix() + redisKey;
            return jedis.del(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
