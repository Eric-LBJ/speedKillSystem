package com.aikeeper.speed.kill.system.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/3 11:43
 * @Version V1.0
 **/
@Data
@ToString
public class OrderDetailVo implements Serializable {

    private static final long serialVersionUID = -3311635409801442330L;

    /**
     * 商品信息
     */
    private GoodsInfoVO goodsInfoVO;

    /**
     * 订单信息
     */
    private OrderInfoVO orderInfoVO;
}
