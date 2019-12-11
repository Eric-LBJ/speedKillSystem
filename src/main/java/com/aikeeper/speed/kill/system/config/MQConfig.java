package com.aikeeper.speed.kill.system.config;

import com.aikeeper.speed.kill.system.comm.Constans;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

    /**
     * Direct模式  交换机Exchange
     * 第二个参数指是否需要序列化
     */
    @Bean
    public Queue queue() {
        return new Queue(Constans.QUEUE_NAME, Boolean.TRUE);
    }

    /**
     * topic模式  交换机Exchange
     */
    @Bean
    public Queue topicQueueFirst() {
        return new Queue(Constans.TOPIC_FIRST, Boolean.TRUE);
    }

    @Bean
    public Queue topicQueueSecond() {
        return new Queue(Constans.TOPIC_SECOND, Boolean.TRUE);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(Constans.TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingFirstTopic(){
        return BindingBuilder.bind(topicQueueFirst()).to(topicExchange()).with(Constans.TOPIC_KEY_FIRST);
    }

    @Bean
    public Binding bindingSecondTopic(){
        return BindingBuilder.bind(topicQueueSecond()).to(topicExchange()).with(Constans.TOPIC_KEY_SECOND);
    }

}
