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

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{
  @Autowired
  UserService userService;

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
