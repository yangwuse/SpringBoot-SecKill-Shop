package com.seckill.service.impl;

import com.seckill.dao.UserDOMapper;
import com.seckill.dao.UserPasswordDOMapper;
import com.seckill.dataobject.UserDO;
import com.seckill.dataobject.UserPasswordDO;
import com.seckill.error.BusinessErrorEnum;
import com.seckill.error.BusinessException;
import com.seckill.service.UserService;
import com.seckill.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  @Transactional
  public void register(UserModel userModel) throws BusinessException {
    if (userModel == null) {
      throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR);
    }

    if (StringUtils.isEmpty(userModel.getName())
        || userModel.getGender() == null
        || userModel.getAge() == null
        || StringUtils.isEmpty(userModel.getPhone())) {
      throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR);
    }

    // 实现Model对象到DO对象的方法
    UserDO userDO = converFromModel(userModel);
    userDOMapper.insertSelective(userDO);

    UserPasswordDO userPasswordDO = converPasswordFromModel(userModel);
    userPasswordDOMapper.insertSelective(userPasswordDO);

    return;
  }

  private UserPasswordDO converPasswordFromModel(UserModel userModel) {
    if (userModel == null) return null;

    UserPasswordDO userPasswordDO = new UserPasswordDO();
    userPasswordDO.setEncrptPasswod(userModel.getEncrptPassword());
    userPasswordDO.setUserId(userModel.getId());
    return userPasswordDO;
  }

  private UserDO converFromModel(UserModel userModel) {
    if (userModel == null) return null;

    UserDO userDO = new UserDO();
    BeanUtils.copyProperties(userModel, userDO);
    return userDO;
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
