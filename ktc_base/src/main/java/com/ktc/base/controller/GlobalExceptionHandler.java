package com.ktc.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 余俊锋
 * @date 2020/11/23 11:00
 * @Description
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        return new Result(StatusCode.ERROR,false, e.getMessage());
    }
}
