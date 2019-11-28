package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.domain.vo.SpeedKillGoodsInfoVO;

import java.util.List;

/**
 * @author
 */
public interface SpeedKillGoodsInfoService {

    /**
     * 删除秒杀商品信息
     *
     * @param id
     * @return
     */
    Boolean deleteByPrimaryKey(Long id);

    /**
     * 新增秒杀商品信息
     *
     * @param record
     * @return
     */
    Boolean insert(SpeedKillGoodsInfoVO record);

    /**
     * 根据id获取秒杀商品信息
     *
     * @param id
     * @return
     */
    SpeedKillGoodsInfoVO selectByPrimaryKey(Long id);

    /**
     * 获取秒杀商品信息列表
     *
     * @return
     */
    List<SpeedKillGoodsInfoVO> selectAll();

    /**
     * 更新秒杀商品信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(SpeedKillGoodsInfoVO record);

    /**
     * 根据商品编号更新秒杀商品信息
     *
     * @param record
     * @return
     */
    Boolean updateByGoodsId(SpeedKillGoodsInfoVO record);

    /**
     * 根据商品编号获取秒杀商品信息
     *
     * @param goodsId
     * @return
     */
    SpeedKillGoodsInfoVO getSpeedKillGoodsInfoByGoodsId(Long goodsId);
}