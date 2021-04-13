package com.seckill.service.impl;

import com.seckill.dao.UserDOMapper;
import com.seckill.dao.UserPasswordDOMapper;
import com.seckill.dataobject.UserDO;
import com.seckill.dataobject.UserPasswordDO;
import com.seckill.service.UserService;
import com.seckill.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDOMapper userDOMapper;

  @Autowired
  private UserPasswordDOMapper userPasswordDOMapper;

  @Override
  public UserModel getUserById(Integer id) {
    UserDO userDO = userDOMapper.selectByPrimaryKey(id);
    if (userDO == null) return null;
    // 通过用户id获取用户加密信息
    UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
    return convertFromDataObject(userDO, userPasswordDO);
  }

  private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
    if (userDO == null) return null;

    UserModel userModel = new UserModel();
    BeanUtils.copyProperties(userDO, userModel);
    if (userPasswordDO != null)
      userModel.setEncrptPassword(userPasswordDO.getEncrptPasswod());
    return userModel;
  }
}
