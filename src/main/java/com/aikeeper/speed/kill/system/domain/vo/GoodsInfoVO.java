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
public class GoodsInfoVO implements Serializable {

    private static final long serialVersionUID = 2132718444655682923L;

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品库存
     */
    private Integer goodsStock;

    /**
     * 商品详情
     */
    private String goodsDetail;

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