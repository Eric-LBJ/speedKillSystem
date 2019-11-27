package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.OrderInfoComponent;
import com.aikeeper.speed.kill.system.dal.OrderInfoMapper;
import com.aikeeper.speed.kill.system.domain.dto.OrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.OrderInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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
    public Boolean insert(OrderInfoDTO record) {
        return null;
    }

    @Override
    public OrderInfoDTO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<OrderInfoDTO> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(OrderInfoDTO record) {
        return null;
    }

    public OrderInfoDTO infoToDto(OrderInfo info){
        OrderInfoDTO dto = new OrderInfoDTO();
        if (!ObjectUtils.isEmpty(info)){
            BeanUtils.copyProperties(info,dto);
        }
        return dto;
    }

    public OrderInfo dtoToInfo(OrderInfoDTO dto){
        OrderInfo info = new OrderInfo();
        if (!ObjectUtils.isEmpty(dto)){
            BeanUtils.copyProperties(dto,info);
        }
        return info;
    }
}