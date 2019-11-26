package com.aikeeper.speed.kill.system.exception;

import com.aikeeper.speed.kill.system.result.CodeMessage;
import lombok.Data;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 17:56
 * @Version V1.0
 **/
@Data
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -3574524704134342360L;

    private CodeMessage codeMessage;

    public GlobalException(CodeMessage codeMessage){
        super(codeMessage.toString());
        this.codeMessage = codeMessage;
    }
}
