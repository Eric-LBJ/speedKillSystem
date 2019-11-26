package com.aikeeper.speed.kill.system.result;

import com.aikeeper.speed.kill.system.comm.Constans;
import lombok.Data;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/22 17:38
 * @Version V1.0
 **/
@Data
public class CodeMessage {

    /**
     * 错误返回码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    public CodeMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeMessage fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.message, args);
        return new CodeMessage(code, message);
    }


    /***
     * System
     */
    public static CodeMessage SUCCESS = new CodeMessage(Constans.SUCCESS_CODE, Constans.SUCCESS_MESSAGE);
    public static CodeMessage SERVER_ERROR = new CodeMessage(Constans.SERVER_ERROR_CODE, Constans.SERVER_ERROR_MESSAGE);
    public static CodeMessage BIND_ERROR = new CodeMessage(500101, "参数校验异常：%s");

    /**
     * Login 500200-500299
     */
    public static CodeMessage PASSWORD_OR_MOBILE_CAN_NOT_BE_NULL = new CodeMessage(500200, "密码和手机号码不能为空");
    public static CodeMessage MOBILE_FORMAT_ERROR = new CodeMessage(500201, "手机号码格式错误");
    public static CodeMessage MOBILE_NOT_EXISTS = new CodeMessage(500202, "该手机号码未注册");
    public static CodeMessage PASSWORD_ERROR = new CodeMessage(500203, "密码错误");

}
