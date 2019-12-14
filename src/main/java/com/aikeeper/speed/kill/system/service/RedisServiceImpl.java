package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.RedisService;
import com.aikeeper.speed.kill.system.comm.keyclass.KeyPrefix;
import com.aikeeper.speed.kill.system.dal.RedisDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/24 17:10
 * @Version V1.0
 **/
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisDao redisDao;

    @Override
    public <T> T get(KeyPrefix prefix, String redisKey, Class<T> clazz) {
        return redisDao.get(prefix, redisKey, clazz);
    }

    @Override
    public <T> Boolean set(KeyPrefix prefix, String redisKey, T value) {
        return redisDao.set(prefix, redisKey, value);
    }

    @Override
    public Boolean exists(KeyPrefix prefix, String redisKey) {
        return redisDao.exists(prefix, redisKey);
    }

    @Override
    public <T> Long incr(KeyPrefix prefix, String redisKey) {
        return redisDao.incr(prefix, redisKey);
    }

    @Override
    public <T> Long decr(KeyPrefix prefix, String redisKey) {
        return redisDao.decr(prefix, redisKey);
    }

    @Override
    public <T> Long delete(KeyPrefix prefix, String redisKey) {
        return redisDao.delete(prefix, redisKey);
    }
}
