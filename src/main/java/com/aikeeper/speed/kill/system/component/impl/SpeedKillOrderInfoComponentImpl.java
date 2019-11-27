package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.SpeedKillOrderInfoComponent;
import com.aikeeper.speed.kill.system.dal.SpeedKillOrderInfoMapper;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillOrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillOrderInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Component
public class SpeedKillOrderInfoComponentImpl implements SpeedKillOrderInfoComponent {

    @Resource
    private SpeedKillOrderInfoMapper speedKillOrderInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(SpeedKillOrderInfoDTO record) {
        return null;
    }

    @Override
    public SpeedKillOrderInfoDTO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<SpeedKillOrderInfoDTO> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillOrderInfoDTO record) {
        return null;
    }

    public SpeedKillOrderInfoDTO infoToDto(SpeedKillOrderInfo info){
        SpeedKillOrderInfoDTO dto = new SpeedKillOrderInfoDTO();
        if (!ObjectUtils.isEmpty(info)){
            BeanUtils.copyProperties(info,dto);
        }
        return dto;
    }

    public SpeedKillOrderInfo dtoToInfo(SpeedKillOrderInfoDTO dto){
        SpeedKillOrderInfo info = new SpeedKillOrderInfo();
        if (!ObjectUtils.isEmpty(dto)){
            BeanUtils.copyProperties(dto,info);
        }
        return info;
    }

}