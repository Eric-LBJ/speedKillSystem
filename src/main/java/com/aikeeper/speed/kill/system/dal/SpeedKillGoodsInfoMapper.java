package com.aikeeper.speed.kill.system.dal;

import com.aikeeper.speed.kill.system.domain.info.SpeedKillGoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */
@Mapper
public interface SpeedKillGoodsInfoMapper {

    /**
     * 删除秒杀商品信息
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Long id);

    /**
     * 新增秒杀商品信息
     *
     * @param record
     * @return
     */
    Integer insert(SpeedKillGoodsInfo record);

    /**
     * 根据id获取秒杀商品信息
     *
     * @param id
     * @return
     */
    SpeedKillGoodsInfo selectByPrimaryKey(Long id);

    /**
     * 获取秒杀商品信息列表
     *
     * @return
     */
    List<SpeedKillGoodsInfo> selectAll();

    /**
     * 更新秒杀商品信息
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(SpeedKillGoodsInfo record);

    /**
     * 根据商品编号更新秒杀商品信息
     *
     * @param record
     * @return
     */
    Integer updateByGoodsId(SpeedKillGoodsInfo record);

    /**
     * 根据商品编号获取秒杀商品信息
     *
     * @param goodsId
     * @return
     */
    SpeedKillGoodsInfo getSpeedKillGoodsInfoByGoodsId(@Param("goodsId") Long goodsId);
}