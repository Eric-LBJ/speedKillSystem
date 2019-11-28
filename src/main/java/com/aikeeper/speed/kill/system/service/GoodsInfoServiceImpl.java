package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.GoodsInfoService;
import com.aikeeper.speed.kill.system.component.GoodsInfoComponent;
import com.aikeeper.speed.kill.system.domain.dto.GoodsInfoDTO;
import com.aikeeper.speed.kill.system.domain.vo.GoodsInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author
 */
@Service("goodsInfoService")
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    private GoodsInfoComponent goodsInfoComponent;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return goodsInfoComponent.deleteByPrimaryKey(id);
    }

    @Override
    public Boolean insert(GoodsInfoVO record) {
        return goodsInfoComponent.insert(voToDto(record));
    }

    @Override
    public GoodsInfoVO selectByPrimaryKey(Long id) {
        return dtoToVo(goodsInfoComponent.selectByPrimaryKey(id));
    }

    @Override
    public List<GoodsInfoVO> selectAll() {
        return goodsInfoComponent.selectAll().stream().map(item -> dtoToVo(item)).collect(Collectors.toList());
    }

    @Override
    public Boolean updateByPrimaryKey(GoodsInfoVO record) {
        return goodsInfoComponent.updateByPrimaryKey(voToDto(record));
    }

    @Override
    public List<GoodsInfoVO> listSpeedKillGoods() {
        return goodsInfoComponent.listSpeedKillGoods().stream().map(item -> dtoToVo(item)).collect(Collectors.toList());
    }

    public GoodsInfoVO dtoToVo(GoodsInfoDTO info) {
        GoodsInfoVO vo = new GoodsInfoVO();
        if (!ObjectUtils.isEmpty(info)) {
            BeanUtils.copyProperties(info, vo);
        }
        return vo;
    }

    public GoodsInfoDTO voToDto(GoodsInfoVO vo) {
        GoodsInfoDTO dto = new GoodsInfoDTO();
        if (!ObjectUtils.isEmpty(vo)) {
            BeanUtils.copyProperties(vo, dto);
        }
        return dto;
    }
}