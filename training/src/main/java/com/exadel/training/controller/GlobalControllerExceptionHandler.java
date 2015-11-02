package com.exadel.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
public class GlobalControllerExceptionHandler {
//    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Object handleConflict(Exception exception) {
//        Map<String, String> errorMessage = new HashMap<String, String>();
//        errorMessage.put("errorMessage", exception.getMessage());
//        return errorMessage;
//    }
}
