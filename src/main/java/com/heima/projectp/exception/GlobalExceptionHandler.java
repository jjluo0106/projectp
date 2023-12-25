package com.heima.projectp.exception;
import com.heima.projectp.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("操作失敗，請聯繫管理員");
    }
}
