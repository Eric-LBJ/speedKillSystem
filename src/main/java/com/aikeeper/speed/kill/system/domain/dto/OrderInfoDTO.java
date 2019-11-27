package com.aikeeper.speed.kill.system.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 */
@Data
@ToString
public class OrderInfoDTO implements Serializable {

    private static final long serialVersionUID = -317679331073690031L;

    /**
     * 订单id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 收获地址id
     */
    private Long deliveryAddId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private Integer goodsCount;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 1pc,2android,3ios
     */
    private Byte orderChannel;

    /**
     * 订单状态：0新建未支付，1待发货，2已发货，3已收货，4已退款，5已完成
     */
    private Byte status;

    /**
     * 订单的创建时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;

}