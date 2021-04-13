package com.seckill.controller;

import com.seckill.controller.viewobject.UserVO;
import com.seckill.service.UserService;
import com.seckill.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {
  @Autowired
  UserService userService;

  @RequestMapping("/get")
  @ResponseBody
  public UserVO getUser(@RequestParam(name="id")Integer id) {
    UserModel userModel = userService.getUserById(id);
    if (userModel == null) return null;
    // 将核心领域模型用户转化为可供UI使用的viewobject
    return convertFromModel(userModel);
  }

  private UserVO convertFromModel(UserModel userModel) {
    if (userModel == null) return null;
    UserVO userVO = new UserVO();
    BeanUtils.copyProperties(userModel, userVO);
    return userVO;
  }
}
