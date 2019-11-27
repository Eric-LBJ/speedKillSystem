package com.aikeeper.speed.kill.system.component;

import com.aikeeper.speed.kill.system.domain.info.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

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
    Boolean insert(OrderInfo record);

    /**
     * 根据id获取订单信息
     *
     * @param id
     * @return
     */
    OrderInfo selectByPrimaryKey(Long id);

    /**
     * 获取订单信息列表
     *
     * @return
     */
    List<OrderInfo> selectAll();

    /**
     * 更新订单信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(OrderInfo record);
}