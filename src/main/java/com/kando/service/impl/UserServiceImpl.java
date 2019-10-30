package com.kando.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import com.kando.service.UserRoleService;
import com.kando.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kando.common.exception.MeioException;
import com.kando.common.exception.ResultEnum;
import com.kando.dao.UserDao;
import com.kando.dto.Result;
import com.kando.entity.User;
import com.kando.util.Random;
import com.kando.util.TestDate;
import com.kando.vo.PageVo;

import javax.annotation.Resource;

/**
 * @author 孙雨佳
 * @ClassName: UserServiceImpl
 * @Description: TODO业务层
 * @date 2019年10月18日
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private StringRedisTemplate redis;


    /**
     * @return Result
     * @Title: loginByPwd
     * @Description:登陆操作-手机号密码登陆
     */
    @Override
    public ResultEnum loginByPwd(User user) {
        String password = user.getPassword();
        String phone = user.getPhone();
        if (StringUtils.isBlank(password) || StringUtils.isBlank(phone)) {
            throw new MeioException(ResultEnum.PHONE_STOCK_ERROR);
        }
        User user1 = userDao.login(phone, password);
        if (!ObjectUtils.isNotEmpty(user1)) {
            throw new MeioException(ResultEnum.USER_NOT_EXIST_ERROR);
        }
        return ResultEnum.SUCCESS;


    }

    /**
     * @return Result
     * @Title: loginByCode
     * @Description: 登陆操作-手机短信登陆-发送验证码
     */
    @Override
    public ResultEnum loginByCode(User user) {
        String seccode = Random.getRandom();
        String phone = user.getPhone();
        redis.opsForValue().set(phone, seccode, 5, TimeUnit.MINUTES);
        System.out.println("登陆");
        System.out.println(seccode);
        if (ObjectUtils.isEmpty(userDao.selectByphone(phone))) {
            throw new MeioException(ResultEnum.USER_NOT_EXIST_ERROR);
        }
        return ResultEnum.SUCCESS;
    }

    /**
     * @return Result
     * @Title: loginCheckCode
     * @Description: 登陆操作-手机短信登陆-验证验证码
     */
    @Override
    public ResultEnum loginCheckCode(User user) {
        String phone = user.getPhone();
        String seccode1 = redis.opsForValue().get(phone);
        String seccode = user.getSeccode();
        System.out.println("登陆");
        if (StringUtils.isBlank(seccode1)) {
            throw new MeioException(ResultEnum.PHONE_STOCK_ERROR);
        }
        if (!seccode.equals(seccode1)) {
            throw new MeioException(ResultEnum.Code_long_ERROR);
        }
        return ResultEnum.SUCCESS;

    }

    /**
     * @return Result
     * @Title: indexByCode
     * @Description: 注册操作-发送手机验证码
     */
    @Override
    public ResultEnum indexByCode(User user) {
        String seccode = Random.getRandom();
        String phone = user.getPhone();
        redis.opsForValue().set(phone, seccode, 5, TimeUnit.MINUTES);
        System.out.println("注册");
        log.info(seccode);
        if (ObjectUtils.isNotEmpty(userDao.selectByphone(phone))) {
            throw new MeioException(ResultEnum.PHONE_IS_EXIST_ERROR);
        }
        return ResultEnum.SUCCESS;
    }

    /**
     * @return Result
     * @Title: indexCheckCode
     * @Description: 注册操作-验证手机验证码
     */
    @Override
    public ResultEnum indexCheckCode(User user) {
        String phone = user.getPhone();
        String seccode1 = redis.opsForValue().get(phone);
        String seccode = user.getSeccode();
        String user_name = user.getUserName();
        String password = user.getPassword();
        Integer sex = user.getSex();
        System.out.println("验证验证码");
        if (StringUtils.isBlank(seccode1)) {
            throw new MeioException(ResultEnum.Code_long_ERROR);
        }
        if (!seccode.equals(seccode1)) {
            throw new MeioException(ResultEnum.PHONE_STOCK_ERROR);
        }
        User user1 = new User();
        user1.setPhone(phone);
        user1.setSex(sex);
        user1.setUserName(user_name);
        user1.setPassword(password);
        TestDate date = new TestDate();
        user1.setCreateTime(date.getDate());
        userDao.index(user1);
//					//添加默认角色--访客
//					if (role.equals("1")){
//						authorityService.addGuest(phone);
//					}else if(role.equals("2")){
//						authorityService.addDesigner(phone);
//					}else if(role.equals("3")){
//						authorityService.addSpecialist(phone);
//					}
        return ResultEnum.SUCCESS;
    }

    /**
     * @return Result
     * @Title: indexBindEmail
     * @Description: 注册操作-绑定邮箱-发送邮箱验证码
     */
    @Override
    public Result indexBindEmail(User user) {
        Result result = new Result();
        String email = user.getEmail();
        if (StringUtils.isNotBlank(email)) {
            if (ObjectUtils.isEmpty(userDao.selectByemail(email))) {
                String seccode = Random.getRandom();
                redis.opsForValue().set(email, seccode, 5, TimeUnit.MINUTES);
                System.out.println(seccode);
                result.setCode(0);
                result.setMessage("验证码发送成功");
                result.setSuccess(true);
                result.setData(seccode);
            } else {
                result.setCode(1);
                result.setMessage("邮箱已被绑定");
                result.setSuccess(false);
            }
        } else {
            result.setCode(1);
            result.setMessage("邮箱不能为空");
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * @return Result
     * @Title: IndexEmailCode
     * @Description: 注册操作-验证邮箱验证码
     */
    @Override
    public ResultEnum IndexEmailCode(User user) {
        String phone = user.getPhone();
        String email = user.getEmail();
        String seccode1 = redis.opsForValue().get(email);
        String seccode = user.getSeccode();
        System.out.println(email);
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(email)) {
            throw new MeioException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isBlank(seccode1)) {
            throw new MeioException(ResultEnum.EMAIL_NULL_ERROR);
        }
        if (!seccode.equals(seccode1)) {
            throw new MeioException(ResultEnum.EMAIL_STOCK_ERROR);
        }
        User user1 = new User();
        user1.setPhone(phone);
        user1.setEmail(email);
        userDao.updateEmail(user1);
        return ResultEnum.SUCCESS;
    }

    /**
     * @return Result
     * @Title: deleteUser
     * @Description: 用戶管理-刪除用戶
     */
    @Override
    public ResultEnum deleteUser(User user) {
        Integer id = user.getId();
        if (ObjectUtils.isEmpty(id)) {
            throw new MeioException(ResultEnum.PARAM_ERROR);
        }
        userDao.deleteByid(id);
        return ResultEnum.SUCCESS;
    }

    /**
     * @return PageInfo<User>
     * @Title: selectUser
     * @Description: 用戶管理-查找用户-分页查询
     */
    @Override
    public PageInfo<User> selectUser(PageVo pageVo) {
        Integer pageNum = pageVo.getPage();
        Integer pageSize = pageVo.getLimit();
        PageHelper.startPage(pageNum, pageSize);
        String Key = pageVo.getKey();
        System.out.println(Key);
        List<User> user1 = userDao.selectAll(Key);
        PageInfo<User> pageInfo = new PageInfo<User>(user1);
        return pageInfo;
    }

    /**
     * @return Result
     * @Title: updateUser1
     * @Description: 用戶管理-修改用户
     */
    @Override
    public User updateUser(User user) {
        Integer id = user.getId();
        User user1 = userDao.selectByid(id);
        return user1;
    }

    /**
     * @return Result
     * @Title: updateUser
     * @Description: 用戶管理-修改用户
     */
    @Override
    public ResultEnum updateUser1(User user) {
        User user1 = new User();
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setPhone(user.getPhone());
        user1.setSex(user.getSex());
        user1.setStatus(user.getStatus());
        user1.setEmail(user.getEmail());
        user1.setId(user.getId());
        userDao.update(user1);
        return ResultEnum.SUCCESS;
    }
}