package com.aikeeper.speed.kill.system.mq;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillMessage;
import com.aikeeper.speed.kill.system.utils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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

    /**
     * 普通消息
     *
     * @param message
     */
    public void send(Object message) {
        String data = ConvertUtils.beanToString(message);
        logger.info("send data : " + data);
        amqpTemplate.convertAndSend(Constans.QUEUE_NAME, data);
    }

    /**
     * topic模式
     *
     * @param message
     */
    public void sendWithTopic(Object message) {
        String data = ConvertUtils.beanToString(message);
        logger.info("send topic data : " + data);
        amqpTemplate.convertAndSend(Constans.TOPIC_EXCHANGE, Constans.TOPIC_KEY_FIRST, data + "---First");
        amqpTemplate.convertAndSend(Constans.TOPIC_EXCHANGE, Constans.TOPIC_KEY_SECOND, data + "---Second");
    }

    /**
     * 广播模式
     * convertAndSend方法的第二个参数routingKey一定要加,值传空即可
     *
     * @param message
     */
    public void sendWithFanout(Object message) {
        String data = ConvertUtils.beanToString(message);
        logger.info("send fanout data : " + data);
        amqpTemplate.convertAndSend(Constans.FANOUT_EXCHANGE, "*", data);
    }

    /**
     * headers模式
     * convertAndSend方法的第二个参数routingKey一定要加,值传空即可
     * messageProperties的setHeader方法里面的值需要和之前MQConfig中where中map放入的值一样
     *
     * @param message
     */
    public void sendWithHeaders(Object message) {
        String data = ConvertUtils.beanToString(message);
        logger.info("send headers data : " + data);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("firstKey", "firstValue");
        messageProperties.setHeader("secondKey", "secondValue");
        Message obj = new Message(data.getBytes(), messageProperties);
        amqpTemplate.convertAndSend(Constans.HEADERS_EXCHANGE, "", obj);
    }


    public void sendSpeedKillMessage(SpeedKillMessage speedKillMessage) {
        String data = ConvertUtils.beanToString(speedKillMessage);
        logger.info("sendSpeedKillMessage send data : " + data);
        amqpTemplate.convertAndSend(Constans.SPEED_KILL_QUEUE_NAME, data);
    }
}
