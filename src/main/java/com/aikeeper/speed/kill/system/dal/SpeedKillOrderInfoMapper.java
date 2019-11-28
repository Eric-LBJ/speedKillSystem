package com.aikeeper.speed.kill.system.dal;

import com.aikeeper.speed.kill.system.domain.info.SpeedKillOrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */
@Mapper
public interface SpeedKillOrderInfoMapper {

    /**
     * 删除秒杀订单信息
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Long id);

    /**
     * 新增秒杀订单信息
     *
     * @param record
     * @return
     */
    Integer insert(SpeedKillOrderInfo record);

    /**
     * 根据id获取秒杀订单信息
     *
     * @param id
     * @return
     */
    SpeedKillOrderInfo selectByPrimaryKey(Long id);

    /**
     * 获取秒杀订单信息列表
     *
     * @return
     */
    List<SpeedKillOrderInfo> selectAll();

    /**
     * 更新秒杀订单信息
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(SpeedKillOrderInfo record);

    /**
     * 根据用户id和商品id获取秒杀订单信息
     *
     * @param userId
     * @param goodsId
     * @return
     */
    SpeedKillOrderInfo getSpeedKillOrderInfoByUserAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);
}