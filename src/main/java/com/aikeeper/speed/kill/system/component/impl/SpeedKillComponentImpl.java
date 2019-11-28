package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.component.*;
import com.aikeeper.speed.kill.system.domain.dto.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/28 14:41
 * @Version V1.0
 **/
@Component
public class SpeedKillComponentImpl implements SpeedKillComponent {

    @Resource
    private GoodsInfoComponent goodsInfoComponent;

    @Resource
    private SpeedKillGoodsInfoComponent speedKillGoodsInfoComponent;

    @Resource
    private OrderInfoComponent orderInfoComponent;

    @Resource
    private SpeedKillOrderInfoComponent speedKillOrderInfoComponent;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OrderInfoDTO speedKillGoods(SpeedKillUserDTO speedKillUserDTO, GoodsInfoDTO goodsInfoDTO) {
        /**
         * 1、减少库存
         */
        Boolean goodsStockIsReduce = goodsInfoComponent.updateByPrimaryKey(packageGoodsDto(goodsInfoDTO));
        Boolean speedKillGoodsStockIsReduce = speedKillGoodsInfoComponent.updateByGoodsId(packageSpeedKillGoodsDto(goodsInfoDTO));

        /**
         * 向订单表插入数据
         */
        OrderInfoDTO orderInfoDTO = orderInfoComponent.insert(packageOrderInfoDto(speedKillUserDTO, goodsInfoDTO));
        Boolean speedKillOrderInfoIsInsert = speedKillOrderInfoComponent.insert(packageSpeedKillOrderInfoDto(speedKillUserDTO,orderInfoDTO));

        if (goodsStockIsReduce && speedKillGoodsStockIsReduce && speedKillOrderInfoIsInsert){
            return orderInfoDTO;
        }
        return null;
    }

    private SpeedKillOrderInfoDTO packageSpeedKillOrderInfoDto(SpeedKillUserDTO speedKillUserDTO, OrderInfoDTO orderInfoDTO) {
        SpeedKillOrderInfoDTO speedKillOrderInfoDTO = new SpeedKillOrderInfoDTO();
        speedKillOrderInfoDTO.setOrderId(orderInfoDTO.getId());
        speedKillOrderInfoDTO.setUserId(speedKillUserDTO.getId());
        speedKillOrderInfoDTO.setGoodsId(orderInfoDTO.getGoodsId());
        return speedKillOrderInfoDTO;
    }

    private OrderInfoDTO packageOrderInfoDto(SpeedKillUserDTO speedKillUserDTO, GoodsInfoDTO goodsInfoDTO) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setUserId(speedKillUserDTO.getId());
        orderInfoDTO.setGoodsId(goodsInfoDTO.getId());
        orderInfoDTO.setDeliveryAddId(Constans.DEFAULT_DELIVERY_ADDRESS_ID);
        orderInfoDTO.setGoodsName(goodsInfoDTO.getGoodsName());
        orderInfoDTO.setGoodsCount(Constans.DEFAULT_GOODS_COUNT);
        orderInfoDTO.setGoodsPrice(goodsInfoDTO.getSpeedKillPrice());
        orderInfoDTO.setOrderChannel(Constans.DEFAULT_ORDER_CHANNEL);
        orderInfoDTO.setStatus(Constans.DEFAULT_STATUS);
        orderInfoDTO.setCreateDate(new Date());
        return orderInfoDTO;
    }

    private SpeedKillGoodsInfoDTO packageSpeedKillGoodsDto(GoodsInfoDTO goodsInfoDTO) {
        SpeedKillGoodsInfoDTO dto = new SpeedKillGoodsInfoDTO();
        dto.setGoodsId(goodsInfoDTO.getId());
        dto.setStockCount(Optional
                .ofNullable(speedKillGoodsInfoComponent.getSpeedKillGoodsInfoByGoodsId(goodsInfoDTO.getId()))
                .orElse(new SpeedKillGoodsInfoDTO()).getStockCount() - 1);
        return dto;
    }

    private GoodsInfoDTO packageGoodsDto(GoodsInfoDTO goodsInfoDTO) {
        GoodsInfoDTO dto = new GoodsInfoDTO();
        dto.setId(goodsInfoDTO.getId());
        dto.setGoodsStock(goodsInfoDTO.getGoodsStock() - 1);
        return dto;
    }

}
