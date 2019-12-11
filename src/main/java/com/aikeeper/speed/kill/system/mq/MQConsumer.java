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

    @RabbitListener(queues = Constans.TOPIC_FIRST)
    public void consumerFirstTopic(String data) {
        logger.info("consumer first topic data : " + data);
    }

    @RabbitListener(queues = Constans.TOPIC_SECOND)
    public void consumerSecondTopic(String data) {
        logger.info("consumer second topic data : " + data);
    }

}
