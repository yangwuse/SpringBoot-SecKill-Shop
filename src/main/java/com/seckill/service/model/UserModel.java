package com.seckill.service.model;

public class UserModel {
  // 真正完整的User对象 组合多个分散的User表字段
  private Integer id;
  private String name;
  private Byte gender;
  private Integer age;
  private String phone;
  private String registerMode;
  private String thirdPartyId;

  private String encrptPassword;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Byte getGender() {
    return gender;
  }

  public void setGender(Byte gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRegisterMode() {
    return registerMode;
  }

  public void setRegisterMode(String registerMode) {
    this.registerMode = registerMode;
  }

  public String getThirdPartyId() {
    return thirdPartyId;
  }

  public void setThirdPartyId(String thirdPartyId) {
    this.thirdPartyId = thirdPartyId;
  }

  public String getEncrptPassword() {
    return encrptPassword;
  }

  public void setEncrptPassword(String encrptPassword) {
    this.encrptPassword = encrptPassword;
  }


}
