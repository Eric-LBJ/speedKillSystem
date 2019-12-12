package com.aikeeper.speed.kill.system.component;

import com.aikeeper.speed.kill.system.domain.dto.GoodsInfoDTO;
import com.aikeeper.speed.kill.system.domain.dto.OrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillMessage;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/28 14:41
 * @Version V1.0
 **/
public interface SpeedKillComponent {

    /**
     * 事务：秒杀操作的事务
     *
     * @param speedKillUserDTO
     * @param goodsInfoDTO
     * @return
     */
    OrderInfoDTO speedKillGoods(SpeedKillUserDTO speedKillUserDTO, GoodsInfoDTO goodsInfoDTO);

    /**
     * mq 秒杀
     *
     * @param message
     */
    void mqSpeedKill(SpeedKillMessage message);

    /**
     * 获取秒杀结果
     *
     * @param userId
     * @param goodsId
     * @return
     */
    Long getSpeedKillResult(Long userId, Long goodsId);
}
