package com.ljm.chat.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dominick Li
 * @createTime 2020/3/8 0:31
 * @description 全局异常处理类
 **/
@ControllerAdvice
public class ErrorController {
    private static final String ERROR_MESSAGE = "系统内部错误,请联系管理员！";

    Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(value = Exception.class)
    public String ExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error("globale exception  msg={}", e);
        return ERROR_MESSAGE;
        }
    }




