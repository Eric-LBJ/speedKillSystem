package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.domain.vo.OrderInfoVO;

import java.util.List;

/**
 * @author
 */
public interface OrderInfoService {

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    Boolean deleteByPrimaryKey(Long id);

    /**
     * 新增订单信息
     *
     * @param record
     * @return
     */
    Boolean insert(OrderInfoVO record);

    /**
     * 根据id获取订单信息
     *
     * @param id
     * @return
     */
    OrderInfoVO selectByPrimaryKey(Long id);

    /**
     * 获取订单信息列表
     *
     * @return
     */
    List<OrderInfoVO> selectAll();

    /**
     * 更新订单信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(OrderInfoVO record);
}