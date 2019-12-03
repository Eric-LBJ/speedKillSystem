package com.aikeeper.speed.kill.system.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/3 11:14
 * @Version V1.0
 **/
@Data
@ToString
public class GoodsDetailVo implements Serializable {

    private static final long serialVersionUID = -5878357799593174782L;

    /**
     * 秒杀状态
     */
    private Integer speedKillStatus;

    /**
     * 商品对象
     */
    private GoodsInfoVO goodsInfoVO;

    /**
     * 用户对象
     */
    private SpeedKillUserVO speedKillUserVO;

    /**
     * 秒杀倒计时时间
     */
    private Integer remainSeconds;

}
