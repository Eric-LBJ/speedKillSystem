package com.aikeeper.speed.kill.system.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/27 14:50
 * @Version V1.0
 **/
@Data
@ToString
public class SpeedKillUserVO implements Serializable {

    private static final long serialVersionUID = -7080131713680195347L;

    /**
     * 手机号码
     */
    private String id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密钥
     */
    private String salt;

    /**
     * 头像
     */
    private String head;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 上次登录时间
     */
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    private Integer loginCount;

}
