package com.aikeeper.speed.kill.system.result;

import com.aikeeper.speed.kill.system.comm.Constans;
import lombok.Data;
import org.springframework.util.ObjectUtils;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/22 17:17
 * @Version V1.0
 **/
@Data
public class Result<T> {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 封装成功时的调用
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }

    /**
     * 封装失败时的调用
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMessage codeMessage){
        return new Result<>(codeMessage);
    }

    private Result(T data){
        this.code = Constans.SUCCESS_CODE;
        this.message = Constans.SUCCESS_MESSAGE;
        this.data = data;
    }

    private Result(CodeMessage codeMessage){
        if (ObjectUtils.isEmpty(codeMessage)){
            return;
        }
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
    }

}
