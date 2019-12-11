package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.GoodsInfoComponent;
import com.aikeeper.speed.kill.system.dal.GoodsInfoMapper;
import com.aikeeper.speed.kill.system.domain.dto.GoodsInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.GoodsInfo;
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
public class GoodsInfoComponentImpl implements GoodsInfoComponent {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return goodsInfoMapper.deleteByPrimaryKey(id) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean insert(GoodsInfoDTO record) {
        return goodsInfoMapper.insert(dtoToInfo(record)) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public GoodsInfoDTO selectByPrimaryKey(Long id) {
        return infoToDto(Optional.ofNullable(goodsInfoMapper.selectByPrimaryKey(id)).orElse(new GoodsInfo()));
    }

    @Override
    public List<GoodsInfoDTO> selectAll() {
        return Optional
                .ofNullable(goodsInfoMapper.selectAll())
                .orElse(new ArrayList<>()).stream().map(item -> infoToDto(item)).collect(Collectors.toList());
    }

    @Override
    public Boolean updateByPrimaryKey(GoodsInfoDTO record) {
        return goodsInfoMapper.updateByPrimaryKey(dtoToInfo(record)) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<GoodsInfoDTO> listSpeedKillGoods() {
        return Optional
                .ofNullable(goodsInfoMapper.listSpeedKillGoods())
                .orElse(new ArrayList<>()).stream().map(item -> infoToDto(item)).collect(Collectors.toList());
    }

    @Override
    public Boolean reduceStockByGoodsId(Long id) {
        return goodsInfoMapper.reduceStockByGoodsId(id) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public GoodsInfoDTO infoToDto(GoodsInfo info) {
        GoodsInfoDTO dto = new GoodsInfoDTO();
        if (!ObjectUtils.isEmpty(info)) {
            BeanUtils.copyProperties(info, dto);
        }
        return dto;
    }

    public GoodsInfo dtoToInfo(GoodsInfoDTO dto) {
        GoodsInfo info = new GoodsInfo();
        if (!ObjectUtils.isEmpty(dto)) {
            BeanUtils.copyProperties(dto, info);
        }
        return info;
    }
}