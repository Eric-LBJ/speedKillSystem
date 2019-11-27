package com.aikeeper.speed.kill.system.component;

import com.aikeeper.speed.kill.system.domain.dto.GoodsInfoDTO;

import java.util.List;

/**
 * @author
 */
public interface GoodsInfoComponent {

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
    Boolean insert(GoodsInfoDTO record);

    /**
     * 根据id获取商品信息
     *
     * @param id
     * @return
     */
    GoodsInfoDTO selectByPrimaryKey(Long id);

    /**
     * 获取商品列表信息
     *
     * @return
     */
    List<GoodsInfoDTO> selectAll();

    /**
     * 更新商品信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(GoodsInfoDTO record);
}