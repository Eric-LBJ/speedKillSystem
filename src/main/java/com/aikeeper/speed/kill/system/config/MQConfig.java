package com.aikeeper.speed.kill.system.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/10 17:38
 * @Version V1.0
 **/
@Configuration
public class MQConfig {

    public final static String QUEUE_NAME = "queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, Boolean.TRUE);
    }

}
