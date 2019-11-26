package com.aikeeper.speed.kill.system.domain.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/23 22:53
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -8177628041019603150L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

}
