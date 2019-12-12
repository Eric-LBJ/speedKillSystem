package com.aikeeper.speed.kill.system.component.impl;

import com.aikeeper.speed.kill.system.comm.Constans;
import com.aikeeper.speed.kill.system.comm.keyclass.impl.child.SpeedKillKey;
import com.aikeeper.speed.kill.system.component.*;
import com.aikeeper.speed.kill.system.dal.RedisDao;
import com.aikeeper.speed.kill.system.domain.dto.*;
import com.aikeeper.speed.kill.system.domain.info.SpeedKillMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

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

    @Resource
    private RedisDao redisDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OrderInfoDTO speedKillGoods(SpeedKillUserDTO speedKillUserDTO, GoodsInfoDTO goodsInfoDTO) {
        return speedKill(speedKillUserDTO, goodsInfoDTO);
    }

    @Override
    public void mqSpeedKill(SpeedKillMessage message) {
        /**
         * 判断库存
         */
        SpeedKillGoodsInfoDTO speedKillGoodsInfoDTO = speedKillGoodsInfoComponent.selectByPrimaryKey(message.getGoodsId());
        if (ObjectUtils.isEmpty(speedKillGoodsInfoDTO) || speedKillGoodsInfoDTO.getStockCount() <= 0) {
            redisDao.set(SpeedKillKey.goodsOverKey, "" + speedKillGoodsInfoDTO.getGoodsId(), Boolean.TRUE);
            return;
        }
        /**
         * 判断是否已经秒杀到了，每个用户id只能秒杀一次
         */
        SpeedKillOrderInfoDTO speedKillOrderInfoDTO = speedKillOrderInfoComponent
                .getSpeedKillOrderInfoByUserAndGoodsId(message.getSpeedKillUserDTO().getId(), message.getGoodsId());
        if (!ObjectUtils.isEmpty(speedKillOrderInfoDTO) && !ObjectUtils.isEmpty(speedKillOrderInfoDTO.getId())) {
            return;
        }
        speedKill(message.getSpeedKillUserDTO(), goodsInfoComponent.selectByPrimaryKey(message.getGoodsId()));
    }

    @Override
    public Long getSpeedKillResult(Long userId, Long goodsId) {
        SpeedKillOrderInfoDTO speedKillOrderInfoDTO = speedKillOrderInfoComponent.getSpeedKillOrderInfoByUserAndGoodsId(userId, goodsId);
        if (!ObjectUtils.isEmpty(speedKillOrderInfoDTO) && !StringUtils.isEmpty(speedKillOrderInfoDTO.getOrderId())) {
            return speedKillOrderInfoDTO.getOrderId();
        }
        if (redisDao.exists(SpeedKillKey.goodsOverKey, "" + goodsId)) {
            return -1L;
        }
        return 0L;
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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public OrderInfoDTO speedKill(SpeedKillUserDTO speedKillUserDTO, GoodsInfoDTO goodsInfoDTO) {
        /**
         * 1、减少秒杀商品表库存
         */
        Boolean speedKillGoodsStockIsReduce = speedKillGoodsInfoComponent.reduceStockByGoodsId(goodsInfoDTO.getId());

        if (speedKillGoodsStockIsReduce) {
            /**
             * 2、减少商品表库存
             */
            Boolean goodsStockIsReduce = goodsInfoComponent.reduceStockByGoodsId(goodsInfoDTO.getId());
            /**
             * 3、向订单表插入数据
             */
            OrderInfoDTO orderInfoDTO = orderInfoComponent.insert(packageOrderInfoDto(speedKillUserDTO, goodsInfoDTO));
            Boolean speedKillOrderInfoIsInsert = speedKillOrderInfoComponent.insert(packageSpeedKillOrderInfoDto(speedKillUserDTO, orderInfoDTO));
            return goodsStockIsReduce && speedKillOrderInfoIsInsert ? orderInfoDTO : null;
        }
        return null;
    }

}
