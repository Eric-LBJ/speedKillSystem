package com.aikeeper.speed.kill.system.api;

import com.aikeeper.speed.kill.system.domain.info.SpeedKillOrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author
 */
public interface SpeedKillOrderInfoService {

    /**
     * 删除秒杀订单信息
     *
     * @param id
     * @return
     */
    Boolean deleteByPrimaryKey(Long id);

    /**
     * 新增秒杀订单信息
     *
     * @param record
     * @return
     */
    Boolean insert(SpeedKillOrderInfo record);

    /**
     * 根据id获取秒杀订单信息
     *
     * @param id
     * @return
     */
    SpeedKillOrderInfo selectByPrimaryKey(Long id);

    /**
     * 获取秒杀订单信息列表
     *
     * @return
     */
    List<SpeedKillOrderInfo> selectAll();

    /**
     * 更新秒杀订单信息
     *
     * @param record
     * @return
     */
    Boolean updateByPrimaryKey(SpeedKillOrderInfo record);
}