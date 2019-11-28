package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.SpeedKillGoodsInfoComponent;
import com.aikeeper.speed.kill.system.dal.SpeedKillGoodsInfoMapper;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillGoodsInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillGoodsInfo;
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
public class SpeedKillGoodsInfoComponentImpl implements SpeedKillGoodsInfoComponent {

    @Resource
    private SpeedKillGoodsInfoMapper speedKillGoodsInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return speedKillGoodsInfoMapper.deleteByPrimaryKey(id) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean insert(SpeedKillGoodsInfoDTO record) {
        return speedKillGoodsInfoMapper.insert(dtoToInfo(record)) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public SpeedKillGoodsInfoDTO selectByPrimaryKey(Long id) {
        return infoToDto(Optional.ofNullable(speedKillGoodsInfoMapper.selectByPrimaryKey(id)).orElse(new SpeedKillGoodsInfo()));
    }

    @Override
    public List<SpeedKillGoodsInfoDTO> selectAll() {
        return Optional
                .ofNullable(speedKillGoodsInfoMapper.selectAll())
                .orElse(new ArrayList<>()).stream().map(item -> infoToDto(item)).collect(Collectors.toList());
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillGoodsInfoDTO record) {
        return speedKillGoodsInfoMapper.updateByPrimaryKey(dtoToInfo(record)) > 0 ? Boolean.TRUE : Boolean.FALSE;
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