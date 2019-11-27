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
    public Boolean insert(OrderInfoVO record) {
        return null;
    }

    @Override
    public OrderInfoVO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<OrderInfoVO> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(OrderInfoVO record) {
        return null;
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