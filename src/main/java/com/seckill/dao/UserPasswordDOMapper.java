package com.seckill.dao;

import com.seckill.dataobject.UserDO;
import com.seckill.dataobject.UserPasswordDO;

public interface UserPasswordDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Tue Apr 13 10:49:31 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Tue Apr 13 10:49:31 CST 2021
     */
    int insert(UserPasswordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Tue Apr 13 10:49:31 CST 2021
     */
    int insertSelective(UserPasswordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Tue Apr 13 10:49:31 CST 2021
     */
    UserPasswordDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Tue Apr 13 10:49:31 CST 2021
     */
    int updateByPrimaryKeySelective(UserPasswordDO record);

    UserPasswordDO selectByUserId(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Tue Apr 13 10:49:31 CST 2021
     */
    int updateByPrimaryKey(UserPasswordDO record);
}