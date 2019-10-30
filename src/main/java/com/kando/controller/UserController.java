package com.kando.controller;


import com.kando.common.exception.MeioException;
import com.kando.common.exception.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.kando.dto.Result;
import com.kando.entity.User;
import com.kando.service.UserService;
import com.kando.vo.PageVo;

/**
 * @author 孙雨佳
 * @ClassName: UserController
 * @Description: 控制器类-登陆注册-用户管理
 * @date 2019年10月18日
 */
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @return Result   返回类型
     * @Title: loginByPwd
     * @Description: TODO(登陆操作 - 用户名密码)
     */
    @RequestMapping(value = "/loginByPwd", method = RequestMethod.GET)
    public Result loginByPwd(User user) {
        try {
            Result result = new Result();
            ResultEnum resultEnum = userService.loginByPwd(user);
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: loginByCode
     * @Description: TODO(登陆操作 - 发送手机验证码)
     */

    @RequestMapping(value = "/loginByCode", method = RequestMethod.GET)
    public Result loginByCode(@Validated User user) {
        try {
            ResultEnum resultEnum = userService.loginByCode(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: loginCheckCode
     * @Description: TODO(登陆操作 - 验证验证码)
     */

    @RequestMapping(value = "/loginCheckCode", method = RequestMethod.GET)
    public Result checkCode(@Validated User user) {
        try {
            ResultEnum resultEnum = userService.loginCheckCode(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: indexByCode
     * @Description: TODO(注册操作 - 发送手机验证码)
     */

    @RequestMapping(value = "/indexByCode", method = RequestMethod.GET)
    public Result indexByCode(String phone) {
        try {
            User user = new User();
            user.setPhone(phone);
            ResultEnum resultEnum = userService.indexByCode(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result   返回类型
     * @Title: indexCheckCode
     * @Description: TODO(注册操作 - 验证手机验证码)
     */

    @RequestMapping(value = "/indexCheckCode", method = RequestMethod.POST)
    public Result indexCheckCode(@RequestBody User user) {
        try {
            ResultEnum resultEnum = userService.indexCheckCode(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @throws
     * @Title: indexBindEmail
     * @Description: TODO(注册操作 - 绑定邮箱 - 发送邮箱验证码)
     */

    @RequestMapping(value = "/indexBindEmail", method = RequestMethod.GET)
    public Result indexBindEmail(User user) {
        try {
            Result result = new Result();
            ResultEnum resultEnum = userService.indexBindEmail(user);
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: IndexEmailCode
     * @Description: TODO(注册操作 - 绑定邮箱 - 验证邮箱验证码)
     */

    @RequestMapping(value = "/IndexEmailCode", method = RequestMethod.POST)
    public Result IndexEmailCode(@RequestBody User user) {
        try {
            ResultEnum resultEnum = userService.IndexEmailCode(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return PageInfo<User>    返回类型
     * @Title: selectUser
     * @Description: TODO(查询用户)
     */

    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    public Result selectUser(PageVo pageVo) {
        Result result = new Result();
        PageInfo<User> pageInfo = userService.selectUser(pageVo);
        if (ObjectUtils.isNotEmpty(pageInfo)) {
            result.setCode(0);
            result.setMessage("查询成功");
            result.setSuccess(true);
            result.setData(pageInfo);
        } else {
            result.setCode(1);
            result.setMessage("查询失败");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * @return Result    返回类型
     * @Title: deleteUser
     * @Description: TODO(删除用户)
     */

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Result deleteUser(@RequestBody User user) {
        try {
            ResultEnum resultEnum = userService.deleteUser(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @Title: updateUser
     * @Description: TODO(修改用户 - 点击修改)
     */

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public Result updateUser(Integer id) {
        try {
            Result result = new Result();
            User user1 = userService.updateUser(id);
            result.setData(user1);
            result.setCode(0);
            result.setMessage("点击修改");
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    /**
     * @return Result    返回类型
     * @throws
     * @Title: updateUser1
     * @Description: TODO(修改用户 - 修改成功)
     */

    @RequestMapping(value = "/updateUser1", method = RequestMethod.POST)
    public Result updateUser1(@RequestBody User user) {
        try {
            ResultEnum resultEnum = userService.updateUser1(user);
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            Result result = new Result();
            result.setCode(1);
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }
}