package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.OrderInfoComponent;
import com.aikeeper.speed.kill.system.dal.OrderInfoMapper;
import com.aikeeper.speed.kill.system.domain.info.OrderInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Component
public class OrderInfoComponentImpl implements OrderInfoComponent {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(OrderInfo record) {
        return null;
    }

    @Override
    public OrderInfo selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<OrderInfo> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(OrderInfo record) {
        return null;
    }
}