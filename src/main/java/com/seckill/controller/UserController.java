package com.seckill.controller;

import com.alibaba.druid.util.StringUtils;
import com.seckill.controller.viewobject.UserVO;
import com.seckill.error.BusinessErrorEnum;
import com.seckill.error.BusinessException;
import com.seckill.error.CommonError;
import com.seckill.response.CommonReturnType;
import com.seckill.service.UserService;
import com.seckill.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController{
  @Autowired
  private UserService userService;
  @Autowired
  private HttpServletRequest httpServletRequest;

  // 用户注册接口
  @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
  @ResponseBody
  public CommonReturnType register(@RequestParam(name="phone")String phone,
                                   @RequestParam(name="otpCode")String otpCode,
                                   @RequestParam(name="name")String name,
                                   @RequestParam(name="gender")Integer gender,
                                   @RequestParam(name="age")Integer age,
                                   @RequestParam(name="password")String password
                                   ) throws BusinessException, NoSuchAlgorithmException {
    // 验证手机号和对应optCode是否匹配
    String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(phone);
    if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
      throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "短信验证码不符合");
    }
    // 用户注册流程
    UserModel userModel = new UserModel();
    userModel.setName(name);
    userModel.setGender(new Byte(String.valueOf(gender.intValue())));
    userModel.setAge(age);
    userModel.setPhone(phone);
    userModel.setEncrptPassword(encodeByMD5(password));
    userModel.setRegisterMode("byphone");

    userService.register(userModel);
    return CommonReturnType.create(null);
  }

  public String encodeByMD5(String str) throws NoSuchAlgorithmException{
    // 确定计算方法
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    return Base64.encodeBase64String(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
  }

  // 用户获取opt短信接口
  @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
  @ResponseBody
  public CommonReturnType getOpt(@RequestParam(name="phone")String phone) {
    // 随机生成验证码
    Random random = new Random();
    int randomInt = random.nextInt(99999);
    randomInt += 10000;
    String otpCode = String.valueOf(randomInt);

    // 将验证码关联用户手机号 使用http session方式绑定
    httpServletRequest.getSession().setAttribute(phone, otpCode);

    // 将opt验证码通过短信方式发送给用户 省略
    System.out.println("phone = " + phone + " & optCode = " + otpCode);

    return CommonReturnType.create(null);
  }

  @RequestMapping("/get")
  @ResponseBody
  public CommonReturnType getUser(@RequestParam(name="id")Integer id) throws BusinessException{
    UserModel userModel = userService.getUserById(id);

    // 若获取的对应用户信息不存在
    if (userModel == null)
      throw new BusinessException(BusinessErrorEnum.USER_NOT_EXIST);
      // 将核心领域模型用户转化为可供UI使用的viewobject
    UserVO userVO = convertFromModel(userModel);
    return CommonReturnType.create(userVO);
  }

  private UserVO convertFromModel(UserModel userModel) {
    if (userModel == null) return null;
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(userModel, userVO);
    return userVO;
  }


}
