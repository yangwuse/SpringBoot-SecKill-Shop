package com.seckill.error;

import org.springframework.web.bind.annotation.ExceptionHandler;

// 包装器业务异常类实现
public class BusinessException extends Exception implements CommonError{

  private CommonError commonError;

  // 直接接受BusinessErrorEnum参数构造业务异常
  public BusinessException(CommonError commonError) {
    super(); // 不能少 初始化父类Exception
    this.commonError = commonError;
  }

  // 接收自动以errorMsg构造业务异常
  public BusinessException(CommonError commonError, String errorMsg) {
    super();
    this.commonError = commonError;
    this.commonError.setErrorMsg(errorMsg);
  }
  @Override
  public int getErrorCode() {
    return this.commonError.getErrorCode();
  }

  @Override
  public String getErrorMsg() {
    return this.commonError.getErrorMsg();
  }

  @Override
  public CommonError setErrorMsg(String errorMsg) {
    return this.commonError.setErrorMsg(errorMsg);
  }
}
