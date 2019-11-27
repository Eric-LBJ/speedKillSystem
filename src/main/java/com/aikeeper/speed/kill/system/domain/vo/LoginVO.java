package com.aikeeper.speed.kill.system.domain.vo;

import com.aikeeper.speed.kill.system.validator.IsPhone;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 13:55
 * @Version V1.0
 **/
@Data
@ToString
public class LoginVO {

    /**
     * 手机号码
     */
    @NotNull
    @IsPhone
    private String mobile;

    /**
     * 前端MD5加密密码
     */
    @NotNull
    @Length(max = 64)
    private String password;
}
