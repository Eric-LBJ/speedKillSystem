package com.aikeeper.speed.kill.system.mq;

import com.aikeeper.speed.kill.system.comm.Constans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/10 17:37
 * @Version V1.0
 **/
@Component
public class MQConsumer {

    private Logger logger = LoggerFactory.getLogger(MQConsumer.class);

    @RabbitListener(queues = Constans.QUEUE_NAME)
    public void consumer(String data) {
        logger.info("consumer data : " + data);
    }

    @RabbitListener(queues = Constans.TOPIC_QUEUE_FIRST)
    public void consumerFirstTopicOrFanout(String data) {
        logger.info("consumer first topic or fanout data : " + data);
    }

    @RabbitListener(queues = Constans.TOPIC_QUEUE_SECOND)
    public void consumerSecondTopicOrFanout(String data) {
        logger.info("consumer second topic or fanout data : " + data);
    }

    @RabbitListener(queues = Constans.HEADERS_QUEUE)
    public void consumerHeaders(byte[] data) {
        logger.info("consumer headers data : " + new String(data));
    }

}
