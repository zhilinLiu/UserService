package com.kando.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import com.kando.entity.Role;
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
 * @date 2019年10月28日修改
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
            //手机号或密码不能为空
            throw new MeioException(ResultEnum.PHONE_STOCK_ERROR);
        }
        User user1 = userDao.login(phone, password);
        if (ObjectUtils.isEmpty(user1)) {
            //用户不存在
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
            //用户不存在
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
        System.out.println("验证手机验证码");
        if (StringUtils.isBlank(seccode1)) {
            //验证码超时
            throw new MeioException(ResultEnum.Code_long_ERROR);
        }
        if (!seccode.equals(seccode1)) {
            //手机或验证码不正确
            throw new MeioException(ResultEnum.PHONE_STOCK_ERROR);
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
            //用户已经存在
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
        log.info("验证手机验证码");
        if (StringUtils.isBlank(seccode1)) {
            //验证码超时
            throw new MeioException(ResultEnum.Code_long_ERROR);
        }
        if (!seccode.equals(seccode1)) {
            //手机或验证码不正确
            throw new MeioException(ResultEnum.PHONE_STOCK_ERROR);
        }
        User user1 = new User();
        user1.setPhone(phone);
        user1.setSex(sex);
        user1.setUserName(user_name);
        user1.setPassword(password);
        TestDate date = new TestDate();
        user1.setCreateTime(date.getDate());
        Integer a = userDao.index(user1);
        if(a<=0){
            throw new MeioException(ResultEnum.UNKNOWN_ERROR);
        }
//					//添加默认角色--访客
//					if (role.equals("1")){
//						authorityService.addGuest(phone);
//					}else if(role.equals("2")){
//						authorityService.addDesigner(phone);
//					}else if(role.equals("3")){
//						authorityService.addSpecialist(phone);
//		}
        return ResultEnum.SUCCESS;
    }

    /**
     * @return Result
     * @Title: indexBindEmail
     * @Description: 注册操作-绑定邮箱-发送邮箱验证码
     */
    @Override
    public ResultEnum indexBindEmail(User user) {
        Result result = new Result();
        String email = user.getEmail();
        if (StringUtils.isBlank(email)) {
            //邮箱不能为空
            throw new MeioException(ResultEnum.PARAM_ERROR);
        }
        if (ObjectUtils.isNotEmpty(userDao.selectByemail(email))) {
            //邮箱已经被绑定
            throw new  MeioException(ResultEnum.EMAIL_IS_EXIST_ERROR);
        }
            String seccode = Random.getRandom();
            redis.opsForValue().set(email, seccode, 5, TimeUnit.MINUTES);
            log.info(seccode);
            log.info("发送邮箱验证码");
            return ResultEnum.SUCCESS;
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
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(email)) {
            //手机或邮箱不能为空
            throw new MeioException(ResultEnum.PARAM_ERROR);
        }
        if (StringUtils.isBlank(seccode1)) {
            //验证码超时
            throw new MeioException(ResultEnum.Code_long_ERROR);
        }
        if (!seccode.equals(seccode1)) {
            //邮箱或验证码不正确
            throw new MeioException(ResultEnum.EMAIL_STOCK_ERROR);
        }
        User user1 = new User();
        user1.setPhone(phone);
        user1.setEmail(email);
        Integer a = userDao.updateEmail(user1);
        if(a<=0){
            throw new MeioException(ResultEnum.EMAIL_ERROR);
        }
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
            //id不能为空
            throw new MeioException(ResultEnum.PARAM_ERROR);
        }
        Integer a = userDao.deleteByid(id);
        if(a<=0){
            //删除用户失败
            throw new MeioException(ResultEnum.DELETE_USER_ERROR);
        }
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
        log.info(Key);
        List<User> user1 = userDao.selectAll(Key);
        user1.forEach(user->{
            List<Role> roles=userRoleService.selectRoleId(user.getId());
            if(roles==null || roles.size()==0){
                throw new MeioException(ResultEnum.PARAM_ERROR);
            }
            user.setRoles(roles);
        });
        PageInfo<User> pageInfo = new PageInfo<User>(user1);
        return pageInfo;
    }

    /**
     * @return Result
     * @Title: updateUser1
     * @Description: 用戶管理-修改用户
     */
    @Override
    public User updateUser(Integer id) {
        if(ObjectUtils.isEmpty(id)){
            //id不能为空
            throw new MeioException(ResultEnum.PARAM_ERROR);
        }
        User user1 = userDao.selectByid(id);
        if (ObjectUtils.isEmpty(user1)){
            //用户不存在
            throw new MeioException(ResultEnum.USER_NOT_EXIST_ERROR);
        }
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