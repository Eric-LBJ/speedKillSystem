package com.aikeeper.speed.kill.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/24 16:25
 * @Version V1.0
 **/
@Configuration
public class RedisPoolFactory {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private Integer redisPort;

    @Value("${redis.timeout}")
    private Integer redisTimeout;

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${redis.poolMaxTotal}")
    private Integer redisPoolMaxTotal;

    @Value("${redis.poolMaxIdle}")
    private Integer redisPoolMaxIdle;

    @Value("${redis.poolMaxWait}")
    private Integer redisPoolMaxWait;

    /**
     * 获取jedis连接池
     * @return
     */
    @Bean
    public JedisPool JedisPoolFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisPoolMaxIdle);
        poolConfig.setMaxTotal(redisPoolMaxTotal);
        poolConfig.setMaxWaitMillis(redisPoolMaxWait);
        JedisPool jedisPool = new JedisPool(poolConfig,redisHost,redisPort,redisTimeout,redisPassword);
        return jedisPool;
    }

}
