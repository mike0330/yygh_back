package com.tlm.yygh.common.exception;

import com.tlm.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:TLM
 * Date:2024/1/26
 * Time:17:27
 * Description:全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error (Exception e){
        e.printStackTrace();
        return Result.fail();
    }
    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result error (YyghException e){
        e.printStackTrace();
        return Result.fail();
    }

}
