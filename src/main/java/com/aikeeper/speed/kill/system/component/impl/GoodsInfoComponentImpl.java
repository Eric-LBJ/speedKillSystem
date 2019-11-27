package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.component.GoodsInfoComponent;
import com.aikeeper.speed.kill.system.dal.GoodsInfoMapper;
import com.aikeeper.speed.kill.system.domain.dto.GoodsInfoDTO;
import com.aikeeper.speed.kill.system.domain.info.GoodsInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Component
public class GoodsInfoComponentImpl implements GoodsInfoComponent {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(GoodsInfoDTO record) {
        return null;
    }

    @Override
    public GoodsInfoDTO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<GoodsInfoDTO> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(GoodsInfoDTO record) {
        return null;
    }

    public GoodsInfoDTO infoToDto(GoodsInfo info){
        GoodsInfoDTO dto = new GoodsInfoDTO();
        if (!ObjectUtils.isEmpty(info)){
            BeanUtils.copyProperties(info,dto);
        }
        return dto;
    }

    public GoodsInfo dtoToInfo(GoodsInfoDTO dto){
        GoodsInfo info = new GoodsInfo();
        if (!ObjectUtils.isEmpty(dto)){
            BeanUtils.copyProperties(dto,info);
        }
        return info;
    }
}