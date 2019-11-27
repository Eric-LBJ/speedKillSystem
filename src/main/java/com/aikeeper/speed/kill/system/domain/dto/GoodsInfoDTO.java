package com.aikeeper.speed.kill.system.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author
 */
@Data
@ToString
public class GoodsInfoDTO implements Serializable {

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

}