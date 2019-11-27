package com.aikeeper.speed.kill.system.component;

import com.aikeeper.speed.kill.system.domain.dto.SpeedKillGoodsInfoDTO;

import java.util.List;

/**
 * @author
 */
public interface SpeedKillGoodsInfoComponent {

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
    Boolean insert(SpeedKillGoodsInfoDTO record);

    /**
     * 根据id获取秒杀商品信息
     *
     * @param id
     * @return
     */
    SpeedKillGoodsInfoDTO selectByPrimaryKey(Long id);

    /**
     * 获取秒杀商品信息列表
     *
     * @return
     */
    List<SpeedKillGoodsInfoDTO> selectAll();

    /**
     * 更新秒杀商品信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(SpeedKillGoodsInfoDTO record);
}