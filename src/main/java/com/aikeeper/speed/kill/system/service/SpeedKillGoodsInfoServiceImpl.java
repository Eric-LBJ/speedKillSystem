package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.SpeedKillGoodsInfoService;
import com.aikeeper.speed.kill.system.component.SpeedKillGoodsInfoComponent;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillGoodsInfoDTO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillGoodsInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Service("speedKillGoodsInfoService")
public class SpeedKillGoodsInfoServiceImpl implements SpeedKillGoodsInfoService {

    @Resource
    private SpeedKillGoodsInfoComponent speedKillGoodsInfoComponent;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(SpeedKillGoodsInfoVO record) {
        return null;
    }

    @Override
    public SpeedKillGoodsInfoVO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<SpeedKillGoodsInfoVO> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillGoodsInfoVO record) {
        return null;
    }

    public SpeedKillGoodsInfoVO dtoToVo(SpeedKillGoodsInfoDTO info) {
        SpeedKillGoodsInfoVO vo = new SpeedKillGoodsInfoVO();
        if (!ObjectUtils.isEmpty(info)) {
            BeanUtils.copyProperties(info, vo);
        }
        return vo;
    }

    public SpeedKillGoodsInfoDTO voToDto(SpeedKillGoodsInfoVO vo) {
        SpeedKillGoodsInfoDTO dto = new SpeedKillGoodsInfoDTO();
        if (!ObjectUtils.isEmpty(vo)) {
            BeanUtils.copyProperties(vo, dto);
        }
        return dto;
    }
}