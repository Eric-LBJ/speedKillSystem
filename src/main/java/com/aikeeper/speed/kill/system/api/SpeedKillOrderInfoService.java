package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.domain.vo.SpeedKillOrderInfoVO;

import java.util.List;

/**
 * @author
 */
public interface SpeedKillOrderInfoService {

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
    Boolean insert(SpeedKillOrderInfoVO record);

    /**
     * 根据id获取秒杀订单信息
     *
     * @param id
     * @return
     */
    SpeedKillOrderInfoVO selectByPrimaryKey(Long id);

    /**
     * 获取秒杀订单信息列表
     *
     * @return
     */
    List<SpeedKillOrderInfoVO> selectAll();

    /**
     * 更新秒杀订单信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(SpeedKillOrderInfoVO record);

    /**
     * 根据用户id和商品id获取秒杀订单信息
     *
     * @param userId
     * @param goodsId
     * @return
     */
    SpeedKillOrderInfoVO getSpeedKillOrderInfoByUserAndGoodsId(Long userId, Long goodsId);
}