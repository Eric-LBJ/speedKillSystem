package com.aikeeper.speed.kill.system.domain.info;

import com.aikeeper.speed.kill.system.domain.dto.SpeedKillUserDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/12/11 15:43
 * @Version V1.0
 **/
@Data
@ToString
public class SpeedKillMessage implements Serializable {

    private static final long serialVersionUID = 6027217444374928505L;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 用户信息
     */
    private SpeedKillUserDTO speedKillUserDTO;
}
