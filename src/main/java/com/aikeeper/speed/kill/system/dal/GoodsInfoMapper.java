package com.aikeeper.speed.kill.system.dal;

import com.aikeeper.speed.kill.system.domain.info.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author
 */
@Mapper
public interface GoodsInfoMapper {

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Long id);

    /**
     * 新增商品
     *
     * @param record
     * @return
     */
    Integer insert(GoodsInfo record);

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    GoodsInfo selectByPrimaryKey(Long id);

    /**
     * 获取商品列表信息
     *
     * @return
     */
    List<GoodsInfo> selectAll();

    /**
     * 更新商品信息
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(GoodsInfo record);

    /**
     * 获取秒杀商品信息
     *
     * @return
     */
    List<GoodsInfo> listSpeedKillGoods();

    /**
     * 更新库存
     *
     * @param record
     * @return
     */
    Integer reduceGoodsStock(GoodsInfo record);
}