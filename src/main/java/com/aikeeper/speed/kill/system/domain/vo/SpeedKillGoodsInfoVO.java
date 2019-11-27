package com.aikeeper.speed.kill.system.domain.vo;

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
public class SpeedKillGoodsInfoVO implements Serializable {

    private static final long serialVersionUID = 1004437642142786607L;

    /**
     * 秒杀的商品id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 秒杀单价
     */
    private BigDecimal speedKillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;

}