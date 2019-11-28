package com.aikeeper.speed.kill.system.component;

import com.aikeeper.speed.kill.system.domain.dto.OrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.OrderInfo;

import java.util.List;

/**
 * @author
 */
public interface OrderInfoComponent {

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
    OrderInfoDTO insert(OrderInfoDTO record);

    /**
     * 根据id获取订单信息
     *
     * @param id
     * @return
     */
    OrderInfoDTO selectByPrimaryKey(Long id);

    /**
     * 获取订单信息列表
     *
     * @return
     */
    List<OrderInfoDTO> selectAll();

    /**
     * 更新订单信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(OrderInfoDTO record);
}