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

/**
 * @author
 */
@Service("goodsInfoService")
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    private GoodsInfoComponent goodsInfoComponent;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(GoodsInfoVO record) {
        return null;
    }

    @Override
    public GoodsInfoVO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<GoodsInfoVO> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(GoodsInfoVO record) {
        return null;
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