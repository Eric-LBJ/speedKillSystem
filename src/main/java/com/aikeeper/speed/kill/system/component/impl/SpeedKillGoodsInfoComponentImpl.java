package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.SpeedKillGoodsInfoComponent;
import com.aikeeper.speed.kill.system.dal.SpeedKillGoodsInfoMapper;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillGoodsInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillGoodsInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Component
public class SpeedKillGoodsInfoComponentImpl implements SpeedKillGoodsInfoComponent {

    @Resource
    private SpeedKillGoodsInfoMapper speedKillGoodsInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(SpeedKillGoodsInfoDTO record) {
        return null;
    }

    @Override
    public SpeedKillGoodsInfoDTO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<SpeedKillGoodsInfoDTO> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillGoodsInfoDTO record) {
        return null;
    }

    public SpeedKillGoodsInfoDTO infoToDto(SpeedKillGoodsInfo info){
        SpeedKillGoodsInfoDTO dto = new SpeedKillGoodsInfoDTO();
        if (!ObjectUtils.isEmpty(info)){
            BeanUtils.copyProperties(info,dto);
        }
        return dto;
    }

    public SpeedKillGoodsInfo dtoToInfo(SpeedKillGoodsInfoDTO dto){
        SpeedKillGoodsInfo info = new SpeedKillGoodsInfo();
        if (!ObjectUtils.isEmpty(dto)){
            BeanUtils.copyProperties(dto,info);
        }
        return info;
    }
}