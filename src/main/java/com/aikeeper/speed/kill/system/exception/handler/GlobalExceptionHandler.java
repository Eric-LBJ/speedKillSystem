package com.aikeeper.speed.kill.system.exception.handler;

import com.aikeeper.speed.kill.system.exception.GlobalException;
import com.aikeeper.speed.kill.system.result.CodeMessage;
import com.aikeeper.speed.kill.system.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: TODO
 * @Author ga.zhang
 * @Date 2019/11/25 17:25
 * @Version V1.0
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof GlobalException) {
            GlobalException exception = (GlobalException) e;
            return Result.error(exception.getCodeMessage());
        } else if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> allErrors = exception.getAllErrors();
            AtomicReference<String> errorMessage = new AtomicReference<>("");
            allErrors.forEach(item -> errorMessage.updateAndGet(v -> v + item.getDefaultMessage()));
            return Result.error(CodeMessage.BIND_ERROR.fillArgs(errorMessage.get()));
        } else {
            return Result.error(CodeMessage.SERVER_ERROR);
        }
    }

}
