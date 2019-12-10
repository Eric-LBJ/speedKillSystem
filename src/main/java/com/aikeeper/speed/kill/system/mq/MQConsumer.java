package com.aikeeper.speed.kill.system.mq;

import com.aikeeper.speed.kill.system.config.MQConfig;
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

    /**
     * Direct交换机模式  Exchange
     */
    @RabbitListener(queues = MQConfig.QUEUE_NAME)
    public void consumer(String data) {
        logger.info("consumer data : " + data);
    }

}
