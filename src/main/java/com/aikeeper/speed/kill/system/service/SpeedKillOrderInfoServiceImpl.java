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
import java.util.stream.Collectors;

/**
 * @author
 */
@Service("speedKillOrderInfoService")
public class SpeedKillOrderInfoServiceImpl implements SpeedKillOrderInfoService {

    @Resource
    private SpeedKillOrderInfoComponent speedKillOrderInfoComponent;

    @Override
    public Boolean deleteByPrimaryKey(Long id) {
        return speedKillOrderInfoComponent.deleteByPrimaryKey(id);
    }

    @Override
    public Boolean insert(SpeedKillOrderInfoVO record) {
        return speedKillOrderInfoComponent.insert(voToDto(record));
    }

    @Override
    public SpeedKillOrderInfoVO selectByPrimaryKey(Long id) {
        return dtoToVo(speedKillOrderInfoComponent.selectByPrimaryKey(id));
    }

    @Override
    public List<SpeedKillOrderInfoVO> selectAll() {
        return speedKillOrderInfoComponent.selectAll().stream().map(item -> dtoToVo(item)).collect(Collectors.toList());
    }

    @Override
    public Boolean updateByPrimaryKey(SpeedKillOrderInfoVO record) {
        return speedKillOrderInfoComponent.updateByPrimaryKey(voToDto(record));
    }

    @Override
    public SpeedKillOrderInfoVO getSpeedKillOrderInfoByUserAndGoodsId(Long userId, Long goodsId) {
        return dtoToVo(speedKillOrderInfoComponent.getSpeedKillOrderInfoByUserAndGoodsId(userId, goodsId));
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