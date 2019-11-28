package com.aikeeper.speed.kill.system.component;

import com.aikeeper.speed.kill.system.domain.dto.SpeedKillOrderInfoDTO;

import java.util.List;

/**
 * @author
 */
public interface SpeedKillOrderInfoComponent {

    /**
     * 删除秒杀订单信息
     *
     * @param id
     * @return
     */
    Boolean deleteByPrimaryKey(Long id);

    /**
     * 新增秒杀订单信息
     *
     * @param record
     * @return
     */
    Boolean insert(SpeedKillOrderInfoDTO record);

    /**
     * 根据id获取秒杀订单信息
     *
     * @param id
     * @return
     */
    SpeedKillOrderInfoDTO selectByPrimaryKey(Long id);

    /**
     * 获取秒杀订单信息列表
     *
     * @return
     */
    List<SpeedKillOrderInfoDTO> selectAll();

    /**
     * 更新秒杀订单信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(SpeedKillOrderInfoDTO record);

    /**
     * 根据用户id和商品id获取秒杀订单信息
     *
     * @param userId
     * @param goodsId
     * @return
     */
    SpeedKillOrderInfoDTO getSpeedKillOrderInfoByUserAndGoodsId(Long userId, Long goodsId);
}