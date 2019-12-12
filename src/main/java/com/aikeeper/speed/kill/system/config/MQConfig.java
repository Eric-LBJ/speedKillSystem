package com.aikeeper.speed.kill.system.config;

import com.aikeeper.speed.kill.system.comm.Constans;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


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
     * 第二个参数指是否需要序列化
     */
    @Bean
    public Queue topicQueueFirst() {
        return new Queue(Constans.TOPIC_QUEUE_FIRST, Boolean.TRUE);
    }

    @Bean
    public Queue topicQueueSecond() {
        return new Queue(Constans.TOPIC_QUEUE_SECOND, Boolean.TRUE);
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

    /**
     * FanOut模式  交换机Exchange  --广播模式
     * 第二个参数指是否需要序列化
     * queue沿用topic模式的queue
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(Constans.FANOUT_EXCHANGE);
    }

    @Bean
    public Binding bindingFirstFanout(){
        return BindingBuilder.bind(topicQueueFirst()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingSecondFanout(){
        return BindingBuilder.bind(topicQueueSecond()).to(fanoutExchange());
    }

    /**
     * headers模式  交换机Exchange
     * 第二个参数指是否需要序列化
     * queue沿用topic模式的queue
     */
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(Constans.HEADERS_EXCHANGE);
    }

    @Bean
    public Queue headersQueue() {
        return new Queue(Constans.HEADERS_QUEUE, Boolean.TRUE);
    }

    @Bean
    public Binding bindingHeaders(){
        Map<String,Object> map = new HashMap<>(2);
        map.put("firstKey","firstValue");
        map.put("secondKey","secondValue");
        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
    }

    /**
     * 秒杀优化
     */
    @Bean
    public Queue speedKillQueue() {
        return new Queue(Constans.SPEED_KILL_QUEUE_NAME, Boolean.TRUE);
    }

}
