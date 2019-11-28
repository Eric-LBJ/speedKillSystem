package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.OrderInfoComponent;
import com.aikeeper.speed.kill.system.dal.OrderInfoMapper;
import com.aikeeper.speed.kill.system.domain.dto.OrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.OrderInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author
 */
@Component
public class OrderInfoComponentImpl implements OrderInfoComponent {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return orderInfoMapper.deleteByPrimaryKey(id) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean insert(OrderInfoDTO record) {
        return orderInfoMapper.insert(dtoToInfo(record)) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public OrderInfoDTO selectByPrimaryKey(Long id) {
        return infoToDto(Optional.ofNullable(orderInfoMapper.selectByPrimaryKey(id)).orElse(new OrderInfo()));
    }

    @Override
    public List<OrderInfoDTO> selectAll() {
        return Optional
                .ofNullable(orderInfoMapper.selectAll())
                .orElse(new ArrayList<>()).stream().map(item -> infoToDto(item)).collect(Collectors.toList());
    }

    @Override
    public Boolean updateByPrimaryKey(OrderInfoDTO record) {
        return orderInfoMapper.updateByPrimaryKey(dtoToInfo(record)) > 0 ? Boolean.TRUE : Boolean.FALSE;
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