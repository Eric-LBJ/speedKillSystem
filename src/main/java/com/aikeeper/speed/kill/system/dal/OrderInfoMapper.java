package com.aikeeper.speed.kill.system.dal;

import com.aikeeper.speed.kill.system.domain.info.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author
 */
@Mapper
public interface OrderInfoMapper {

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Long id);

    /**
     * 新增订单信息
     *
     * @param record
     * @return
     */
    Integer insert(OrderInfo record);

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
    Integer updateByPrimaryKey(OrderInfo record);
}