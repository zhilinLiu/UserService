package com.kando.service;


import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.kando.common.exception.ResultEnum;
import com.kando.dto.Result;
import com.kando.entity.User;
import com.kando.vo.PageVo;


/**
 * @author 孙雨佳
 * @ClassName: UserService
 * @Description: 业务层接口
 * @date 2019年10月23日
 */
public interface UserService {
    //手机密码登陆
    ResultEnum loginByPwd(User user);
    //手机验证码登陆-发送验证码
    ResultEnum loginByCode(User user);
    //手机验证码登陆-验证手机验证码
    ResultEnum loginCheckCode(User user);
    //注册-发送手机验证码
    ResultEnum indexByCode(User user);
    //注册-验证手机验证码
    ResultEnum indexCheckCode(User user);
    //注册-绑定邮箱-发送邮箱验证码
    ResultEnum indexBindEmail(User user);
    //注册-绑定邮箱-验证邮箱验证码
    ResultEnum IndexEmailCode(User user);
    //分页查询用户
    PageInfo<User> selectUser(PageVo pageVo);
    //删除用户
    ResultEnum deleteUser(User user);
    //修改用户-返回User
    User updateUser(Integer id);
    //修改成功
    ResultEnum updateUser1(User user);

}