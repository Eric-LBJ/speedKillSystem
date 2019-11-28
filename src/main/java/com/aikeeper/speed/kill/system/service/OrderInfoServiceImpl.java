package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.OrderInfoService;
import com.aikeeper.speed.kill.system.component.OrderInfoComponent;
import com.aikeeper.speed.kill.system.domain.dto.OrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.vo.OrderInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoComponent orderInfoComponent;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return orderInfoComponent.deleteByPrimaryKey(id);
    }

    @Override
    public Boolean insert(OrderInfoVO record) {
        if (!ObjectUtils.isEmpty(orderInfoComponent.insert(voToDto(record))) &&
                !ObjectUtils.isEmpty(orderInfoComponent.insert(voToDto(record)).getId())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public OrderInfoVO selectByPrimaryKey(Long id) {
        return dtoToVo(orderInfoComponent.selectByPrimaryKey(id));
    }

    @Override
    public List<OrderInfoVO> selectAll() {
        return orderInfoComponent.selectAll().stream().map(item -> dtoToVo(item)).collect(Collectors.toList());
    }

    @Override
    public Boolean updateByPrimaryKey(OrderInfoVO record) {
        return orderInfoComponent.updateByPrimaryKey(voToDto(record));
    }

    public OrderInfoVO dtoToVo(OrderInfoDTO info) {
        OrderInfoVO vo = new OrderInfoVO();
        if (!ObjectUtils.isEmpty(info)) {
            BeanUtils.copyProperties(info, vo);
        }
        return vo;
    }

    public OrderInfoDTO voToDto(OrderInfoVO vo) {
        OrderInfoDTO dto = new OrderInfoDTO();
        if (!ObjectUtils.isEmpty(vo)) {
            BeanUtils.copyProperties(vo, dto);
        }
        return dto;
    }
}