package com.aikeeper.speed.kill.system.mq;

import com.aikeeper.speed.kill.system.api.SpeedKillService;
import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillMessage;
import com.aikeeper.speed.kill.system.utils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/10 17:37
 * @Version V1.0
 **/
@Component
public class MQConsumer {

    private Logger logger = LoggerFactory.getLogger(MQConsumer.class);

    @Resource
    private SpeedKillService speedKillService;

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

    @RabbitListener(queues = Constans.SPEED_KILL_QUEUE_NAME)
    public void consumerSpeedKillMessage(String data) {
        logger.info("consumer data : " + data);
        SpeedKillMessage message = ConvertUtils.stringToBean(data, SpeedKillMessage.class);
        /**
         * 秒杀操作
         */
        speedKillService.mqSpeedKill(message);
    }

}
