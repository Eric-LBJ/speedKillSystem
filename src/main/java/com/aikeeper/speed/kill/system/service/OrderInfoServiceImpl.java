package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.OrderInfoService;
import com.aikeeper.speed.kill.system.component.OrderInfoComponent;
import com.aikeeper.speed.kill.system.domain.info.OrderInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoComponent orderInfoComponent;

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