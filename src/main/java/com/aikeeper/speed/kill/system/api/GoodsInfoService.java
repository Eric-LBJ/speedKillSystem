package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.domain.vo.GoodsInfoVO;

import java.util.List;

/**
 * @author
 */
public interface GoodsInfoService {

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return
     */
    Boolean deleteByPrimaryKey(Long id);

    /**
     * 新增商品
     *
     * @param record
     * @return
     */
    Boolean insert(GoodsInfoVO record);

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    GoodsInfoVO selectByPrimaryKey(Long id);

    /**
     * 获取商品列表信息
     *
     * @return
     */
    List<GoodsInfoVO> selectAll();

    /**
     * 更新商品信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(GoodsInfoVO record);

    /**
     * 获取秒杀商品列表信息
     *
     * @return
     */
    List<GoodsInfoVO> listSpeedKillGoods();
}