package com.seckill.controller;

import com.seckill.controller.viewobject.UserVO;
import com.seckill.error.BusinessErrorEnum;
import com.seckill.error.BusinessException;
import com.seckill.error.CommonError;
import com.seckill.response.CommonReturnType;
import com.seckill.service.UserService;
import com.seckill.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController{
  @Autowired
  private UserService userService;

  @Autowired
  private HttpServletRequest httpServletRequest;

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
