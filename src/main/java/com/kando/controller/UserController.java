package com.kando.controller;


import com.kando.common.exception.ResultEnum;
import lombok.extern.slf4j.Slf4j;
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
        Result result = new Result();
        ResultEnum resultEnum = userService.loginByPwd(user);
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setSuccess(true);
        return result;
    }

    /**
     * @return Result    返回类型
     * @Title: loginByCode
     * @Description: TODO(登陆操作 - 发送手机验证码)
     */

    @RequestMapping(value = "/loginByCode", method = RequestMethod.GET)
    public Result loginByCode(@Validated User user) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("验证码发送成功");
        result.setSuccess(userService.loginByCode(user));
        return result;
    }

    /**
     * @return Result    返回类型
     * @Title: loginCheckCode
     * @Description: TODO(登陆操作 - 验证验证码)
     */

    @RequestMapping(value = "/loginCheckCode", method = RequestMethod.GET)
    public Result checkCode(@Validated User user) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("登陆成功");
        result.setSuccess(userService.loginCheckCode(user));
        return result;
    }

    /**
     * @return Result    返回类型

     * @Title: indexByCode
     * @Description: TODO(注册操作 - 发送手机验证码)
     */

    @RequestMapping(value = "/indexByCode", method = RequestMethod.GET)
    public Result indexByCode(String phone) {
        Result result = new Result();
        User user = new User();
        user.setPhone(phone);
        userService.indexByCode(user);
        result.setCode(0);
        result.setMessage("发送验证码成功");
        result.setSuccess(true);
        return result;
    }

    /**
     * @return Result   返回类型
     * @Title: indexCheckCode
     * @Description: TODO(注册操作 - 验证手机验证码)
     */

    @RequestMapping(value = "/indexCheckCode", method = RequestMethod.POST)
    public Result indexCheckCode(@RequestBody User user) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("注册成功");
        result.setSuccess(userService.indexCheckCode(user));
        return result;
    }

    /**
     * @return Result    返回类型
     * @throws
     * @Title: indexBindEmail
     * @Description: TODO(注册操作 - 绑定邮箱 - 发送邮箱验证码)
     */

    @RequestMapping(value = "/indexBindEmail", method = RequestMethod.GET)
    public Result indexBindEmail(User user) {
        return userService.indexBindEmail(user);
    }

    /**
     * @return Result    返回类型
     * @Title: IndexEmailCode
     * @Description: TODO(注册操作 - 绑定邮箱 - 验证邮箱验证码)
     */

    @RequestMapping(value = "/IndexEmailCode", method = RequestMethod.POST)
    public Result IndexEmailCode(@RequestBody User user) {
        Result result = new Result();
        if (userService.IndexEmailCode(user)) {
            result.setCode(0);
            result.setMessage("绑定成功");
            result.setSuccess(true);
        } else {
            result.setCode(1);
            result.setMessage("绑定失败");
            result.setSuccess(false);
        }
        return result;
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
        result.setCode(0);
        result.setMessage("查询成功");
        result.setSuccess(true);
        result.setData(pageInfo);
        return result;
    }

    /**
     * @return Result    返回类型
     * @Title: deleteUser
     * @Description: TODO(删除用户)
     */

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Result deleteUser(@RequestBody User user) {
        Result result = new Result();
        if (userService.deleteUser(user)) {
            result.setCode(0);
            result.setMessage("删除成功");
            result.setSuccess(true);
        } else {
            result.setCode(1);
            result.setMessage("删除失败");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * @return Result    返回类型
     * @Title: updateUser
     * @Description: TODO(修改用户 - 点击修改)
     */

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public Result updateUser(User user) {
        Result result = new Result();
        User user1 = userService.updateUser1(user);
        result.setCode(0);
        result.setData(user1);
        result.setMessage("确认修改");
        return result;

    }

    /**
     * @return Result    返回类型
     * @throws
     * @Title: updateUser1
     * @Description: TODO(修改用户 - 修改成功)
     */

    @RequestMapping(value = "/updateUser1", method = RequestMethod.POST)
    public Result updateUser1(@RequestBody User user) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("修改成功");
        result.setSuccess(userService.updateUser(user));
        return result;
    }
}