package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.domain.vo.GoodsInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.OrderInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/28 14:39
 * @Version V1.0
 **/
public interface SpeedKillService {

    /**
     * 事务：秒杀操作的事务
     *
     * @param speedKillUserVO
     * @param goodsInfoVO
     * @return
     */
    OrderInfoVO speedKillGoods(SpeedKillUserVO speedKillUserVO, GoodsInfoVO goodsInfoVO);

}
