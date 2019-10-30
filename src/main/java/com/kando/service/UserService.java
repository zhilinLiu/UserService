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

    ResultEnum loginByPwd(User user);

    ResultEnum loginByCode(User user);

    ResultEnum loginCheckCode(User user);

    ResultEnum indexByCode(User user);

    ResultEnum indexCheckCode(User user);

    Result<?> indexBindEmail(User user);

    ResultEnum IndexEmailCode(User user);

    PageInfo<User> selectUser(PageVo pageVo);

    ResultEnum deleteUser(User user);

    User updateUser(User user);

    ResultEnum updateUser1(User user);

}