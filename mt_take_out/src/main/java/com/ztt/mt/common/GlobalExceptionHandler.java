package com.ztt.mt.common;

import com.ztt.mt.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice(annotations = {Controller.class, RestController.class})
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException e){
        String message=e.getMessage();
        log.info("message:{}",message);
        String userName ="";
        if(message.contains("Duplicate entry")){
            String[] split= message.split(" ");
            userName=split[2];
        }
        return R.error(userName+":已存在，无法注册");
    }
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException e){
        return R.error(e.getMessage());
    }
}
