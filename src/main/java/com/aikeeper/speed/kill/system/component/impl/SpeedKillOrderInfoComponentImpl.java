package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.SpeedKillOrderInfoComponent;
import com.aikeeper.speed.kill.system.dal.SpeedKillOrderInfoMapper;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillOrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillOrderInfo;
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
public class SpeedKillOrderInfoComponentImpl implements SpeedKillOrderInfoComponent {

    @Resource
    private SpeedKillOrderInfoMapper speedKillOrderInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return speedKillOrderInfoMapper.deleteByPrimaryKey(id) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean insert(SpeedKillOrderInfoDTO record) {
        return speedKillOrderInfoMapper.insert(dtoToInfo(record)) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public SpeedKillOrderInfoDTO selectByPrimaryKey(Long id) {
        return infoToDto(Optional.ofNullable(speedKillOrderInfoMapper.selectByPrimaryKey(id)).orElse(new SpeedKillOrderInfo()));
    }

    @Override
    public List<SpeedKillOrderInfoDTO> selectAll() {
        return Optional
                .ofNullable(speedKillOrderInfoMapper.selectAll())
                .orElse(new ArrayList<>()).stream().map(item -> infoToDto(item)).collect(Collectors.toList());
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillOrderInfoDTO record) {
        return speedKillOrderInfoMapper.updateByPrimaryKey(dtoToInfo(record)) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public SpeedKillOrderInfoDTO getSpeedKillOrderInfoByUserAndGoodsId(Long userId, Long goodsId) {
        return infoToDto(Optional.ofNullable(speedKillOrderInfoMapper
                .getSpeedKillOrderInfoByUserAndGoodsId(userId, goodsId)).orElse(new SpeedKillOrderInfo()));
    }

    public SpeedKillOrderInfoDTO infoToDto(SpeedKillOrderInfo info) {
        SpeedKillOrderInfoDTO dto = new SpeedKillOrderInfoDTO();
        if (!ObjectUtils.isEmpty(info)) {
            BeanUtils.copyProperties(info, dto);
        }
        return dto;
    }

    public SpeedKillOrderInfo dtoToInfo(SpeedKillOrderInfoDTO dto) {
        SpeedKillOrderInfo info = new SpeedKillOrderInfo();
        if (!ObjectUtils.isEmpty(dto)) {
            BeanUtils.copyProperties(dto, info);
        }
        return info;
    }

}