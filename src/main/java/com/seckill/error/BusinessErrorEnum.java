package com.seckill.error;

public enum BusinessErrorEnum implements CommonError{
  // 1000开头通用错误类型
  PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
  UNKNOWN_ERROR(10002, "未知错误"),

  // 2000开头用户信息错误
  USER_NOT_EXIST(20001, "用户不存在")
  ;

  BusinessErrorEnum(int errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  private int errorCode;
  private String errorMsg;


  @Override
  public int getErrorCode() {
    return errorCode;
  }

  @Override
  public String getErrorMsg() {
    return errorMsg;
  }

  @Override
  public CommonError setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg; // 对同一个错误码可以自定义错误信息
    return this;
  }
}
