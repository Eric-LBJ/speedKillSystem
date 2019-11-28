package com.aikeeper.speed.kill.system.domain.info;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 14:44
 * @Version V1.0
 **/
@Data
@ToString
public class SpeedKillUser implements Serializable {

    private static final long serialVersionUID = -347492879421807336L;

    /**
     * 手机号码
     */
    private Long id;

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
