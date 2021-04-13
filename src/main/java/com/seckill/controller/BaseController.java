package com.seckill.controller;

import com.seckill.error.BusinessErrorEnum;
import com.seckill.error.BusinessException;
import com.seckill.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

  public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

  // 定义exceptionhandler处理web controller层未被处理的异常
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Object exceptionHandler(HttpServletRequest request, Exception exception) {
    Map<String, Object> responseData = new HashMap<>();
    if ( exception instanceof BusinessException) {
      BusinessException businessException = (BusinessException)exception;
      responseData.put("errorCode", businessException.getErrorCode());
      responseData.put("errorMsg", businessException.getErrorMsg());
    } else {
      responseData.put("errorCode", BusinessErrorEnum.UNKNOWN_ERROR.getErrorCode());
      responseData.put("errorMsg", BusinessErrorEnum.UNKNOWN_ERROR.getErrorMsg());
    }
    return CommonReturnType.create(responseData, "fail");
  }
}
