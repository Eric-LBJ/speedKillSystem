package com.aikeeper.speed.kill.system.mq;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.utils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/10 17:37
 * @Version V1.0
 **/
@Component
public class MQProvider {

    private Logger logger = LoggerFactory.getLogger(MQConsumer.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    public void send(Object message) {
        String data = ConvertUtils.beanToString(message);
        logger.info("send data : " + data);
        amqpTemplate.convertAndSend(Constans.QUEUE_NAME, data);
    }

    public void sendWithTopic(Object message) {
        String data = ConvertUtils.beanToString(message);
        logger.info("send data : " + data);
        amqpTemplate.convertAndSend(Constans.TOPIC_EXCHANGE, Constans.TOPIC_KEY_FIRST, data + "---First");
        amqpTemplate.convertAndSend(Constans.TOPIC_EXCHANGE, Constans.TOPIC_KEY_SECOND, data + "---Second");
    }

}
