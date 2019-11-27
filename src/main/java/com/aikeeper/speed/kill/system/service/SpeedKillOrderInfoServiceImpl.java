package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.SpeedKillOrderInfoService;
import com.aikeeper.speed.kill.system.component.SpeedKillOrderInfoComponent;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillOrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillOrderInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Service("speedKillOrderInfoService")
public class SpeedKillOrderInfoServiceImpl implements SpeedKillOrderInfoService {

    @Resource
    private SpeedKillOrderInfoComponent speedKillOrderInfoComponent;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public Boolean insert(SpeedKillOrderInfoVO record) {
        return null;
    }

    @Override
    public SpeedKillOrderInfoVO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<SpeedKillOrderInfoVO> selectAll() {
        return null;
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillOrderInfoVO record) {
        return null;
    }

    public SpeedKillOrderInfoVO dtoToVo(SpeedKillOrderInfoDTO info) {
        SpeedKillOrderInfoVO vo = new SpeedKillOrderInfoVO();
        if (!ObjectUtils.isEmpty(info)) {
            BeanUtils.copyProperties(info, vo);
        }
        return vo;
    }

    public SpeedKillOrderInfoDTO voToDto(SpeedKillOrderInfoVO vo) {
        SpeedKillOrderInfoDTO dto = new SpeedKillOrderInfoDTO();
        if (!ObjectUtils.isEmpty(vo)) {
            BeanUtils.copyProperties(vo, dto);
        }
        return dto;
    }
}