package com.seckill.response;

public class CommonReturnType {
  // 前端请求处理状态 "success" 或 "fail"
  // success返回数据 fail返回通用错误码
  private String processStatus;
  private Object returnData;


  // 通用创建方法
  public static CommonReturnType create(Object result) {
    return CommonReturnType.create(result, "successs");
  }

  public static CommonReturnType create(Object result, String processStatus) {
    CommonReturnType type = new CommonReturnType();
    type.setProcessStatus(processStatus);
    type.setReturnData(result);
    return type;

  }



  public String getProcessStatus() {
    return processStatus;
  }

  public void setProcessStatus(String processStatus) {
    this.processStatus = processStatus;
  }

  public Object getReturnData() {
    return returnData;
  }

  public void setReturnData(Object returnData) {
    this.returnData = returnData;
  }
}
