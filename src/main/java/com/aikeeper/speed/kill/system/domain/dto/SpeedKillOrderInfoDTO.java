package com.aikeeper.speed.kill.system.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author
 */
@Data
@ToString
public class SpeedKillOrderInfoDTO implements Serializable {

    private static final long serialVersionUID = 866741459707857447L;

    /**
     * 秒杀订单id
     */
    private Long id;

    /**
     *用户id
     */
    private Long userId;

    /**
     *订单id
     */
    private Long orderId;

    /**
     *商品id
     */
    private Long goodsId;

}