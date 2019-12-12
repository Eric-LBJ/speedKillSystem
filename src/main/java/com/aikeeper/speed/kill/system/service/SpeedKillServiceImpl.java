package com.aikeeper.speed.kill.system.service;

import com.aikeeper.speed.kill.system.api.SpeedKillService;
import com.aikeeper.speed.kill.system.component.SpeedKillComponent;
import com.aikeeper.speed.kill.system.domain.dto.GoodsInfoDTO;
import com.aikeeper.speed.kill.system.domain.dto.OrderInfoDTO;
import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillMessage;
import com.aikeeper.speed.kill.system.domain.vo.GoodsInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.OrderInfoVO;
import com.aikeeper.speed.kill.system.domain.vo.SpeedKillUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/28 14:40
 * @Version V1.0
 **/
@Service("speedKillService")
public class SpeedKillServiceImpl implements SpeedKillService {

    @Resource
    private SpeedKillComponent speedKillComponent;

    @Override
    public OrderInfoVO speedKillGoods(SpeedKillUserVO speedKillUserVO, GoodsInfoVO goodsInfoVO) {
        return dtoToVo(speedKillComponent.speedKillGoods(userVoToDto(speedKillUserVO),goodsVoToDto(goodsInfoVO)));
    }

    @Override
    public void mqSpeedKill(SpeedKillMessage message) {
        speedKillComponent.mqSpeedKill(message);
    }

    @Override
    public Long getSpeedKillResult(Long userId, Long goodsId) {
        return speedKillComponent.getSpeedKillResult(userId,goodsId);
    }

    private SpeedKillUserDTO userVoToDto(SpeedKillUserVO vo) {
        SpeedKillUserDTO dto = new SpeedKillUserDTO();
        if (!ObjectUtils.isEmpty(vo)) {
            BeanUtils.copyProperties(vo, dto);
        }
        return dto;
    }

    public GoodsInfoDTO goodsVoToDto(GoodsInfoVO vo) {
        GoodsInfoDTO dto = new GoodsInfoDTO();
        if (!ObjectUtils.isEmpty(vo)) {
            BeanUtils.copyProperties(vo, dto);
        }
        return dto;
    }

    public OrderInfoVO dtoToVo(OrderInfoDTO info) {
        OrderInfoVO vo = new OrderInfoVO();
        if (!ObjectUtils.isEmpty(info)) {
            BeanUtils.copyProperties(info, vo);
        }
        return vo;
    }
}
