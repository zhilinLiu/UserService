package com.kando.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


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
* @ClassName: UserServiceImpl  
* @Description: TODO业务层
* @author 孙雨佳  
* @date 2019年10月18日  
*    
*/
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;


	@Autowired
	private StringRedisTemplate redis;
	
	
	/** 
	 * @Title: loginByPwd 
	 * @Description:登陆操作-手机号密码登陆
	 * @return Result
	 */
	@Override
	public ResultEnum loginByPwd(User user) {
			String password = user.getPassword();
			String phone = user.getPhone();
			User user1 = userDao.login(phone, password);
			if (!ObjectUtils.isNotEmpty(user1)) {
				throw new MeioException(ResultEnum.USER_NOT_EXIST);
			}
			return ResultEnum.SUCCESS;


	}

	/** 
	 * @Title: loginByCode 
	 * @Description: 登陆操作-手机短信登陆-发送验证码
	 * @return Result
	 */
	@Override
	public Boolean loginByCode(User user) {
			Boolean bool =false;
			String seccode = Random.getRandom();
			String phone = user.getPhone();
			redis.opsForValue().set(phone, seccode, 5, TimeUnit.MINUTES);
			System.out.println("登陆");
			System.out.println(seccode);
				if (ObjectUtils.isNotEmpty(userDao.selectByphone(phone))) {          
					bool = true;
				} else {
					throw new MeioException(ResultEnum.USER_NOT_EXIST);
				}
			return bool;
	}

	/** 
	 * @Title: loginCheckCode 
	 * @Description: 登陆操作-手机短信登陆-验证验证码
	 * @return Result
	 */
	@Override
	public Boolean loginCheckCode(User user) {
			Boolean bool = false;
            String phone = user.getPhone();
            String seccode1 = redis.opsForValue().get(phone);
            String seccode = user.getSeccode();
            System.out.println("登陆");
            if (StringUtils.isNotBlank(seccode1)) {
                if (seccode.equals(seccode1)) {
                    bool = true;
                } else {
                	throw new MeioException(ResultEnum.Code_long_ERROR); 
                }
            } else {
            	throw new MeioException(ResultEnum.PHONE_STOCK_ERROR); 
            }
            return bool;

	}
	
	/** 
	 * @Title: indexByCode 
	 * @Description: 注册操作-发送手机验证码
	 * @return Result
	 */
	@Override
	public Boolean indexByCode(User user) {
			Boolean bool = false;
			String seccode = Random.getRandom();
			String phone = user.getPhone();
			redis.opsForValue().set(phone, seccode, 5, TimeUnit.MINUTES);
			System.out.println("注册");
				if (ObjectUtils.isEmpty(userDao.selectByphone(phone))) {
					bool = true;
					log.info("成功");
				} else {
					log.error("失败");
					throw new MeioException(ResultEnum.PHONE_IS_EXIST);
				}
			return bool;


	}
	/** 
	 * @Title: indexCheckCode 
	 * @Description: 注册操作-验证手机验证码
	 * @return Result
	 */
	@Override
	public Boolean indexCheckCode(User user) {
			Boolean bool = false;
			String phone = user.getPhone();
			String seccode1 = redis.opsForValue().get(phone);
			String seccode = user.getSeccode();
			String user_name = user.getUserName();
			String password = user.getPassword();
			String role = user.getRole();
			Integer sex = user.getSex();
			System.out.println("验证验证码");
			if (StringUtils.isNotBlank(seccode1)) {
				if (seccode.equals(seccode1)) {
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
					bool = true;
				} else {
					throw new MeioException(ResultEnum.PHONE_STOCK_ERROR); 
				}
			} else {
				throw new MeioException(ResultEnum.Code_long_ERROR); 
			}
			return bool;
	}

	/** 
	 * @Title: indexBindEmail 
	 * @Description: 注册操作-绑定邮箱-发送邮箱验证码
	 * @return Result
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
	 * @Title: IndexEmailCode 
	 * @Description: 注册操作-验证邮箱验证码
	 * @return Result
	 */
	@Override
	public Boolean IndexEmailCode(User user) {
			Boolean bool =false;
			String phone = user.getPhone();
			String email = user.getEmail();
			String seccode1 = redis.opsForValue().get(email);
			String seccode = user.getSeccode();
			System.out.println(email);
			if (StringUtils.isNotBlank(seccode1)) {
				if (seccode.equals(seccode1)) {
					User user1 = new User();
					user1.setPhone(phone);
					user1.setEmail(email);
					userDao.updateEmail(user1);
					bool = true;
				} else {
					throw new MeioException(ResultEnum.EMAIL_STOCK_ERROR); 
				}
			} else {
				throw new MeioException(ResultEnum.EMAIL_NULL_ERROR);
			}
			return bool;
	}
	/** 
	 * @Title: deleteUser
	 * @Description: 用戶管理-刪除用戶
	 * @return Result
	 */
	@Override
	public Boolean deleteUser(User user) {
		Integer id = user.getId();
		Boolean bool = false;
		if(userDao.deleteByid(id)>0) {
			bool = true;
		}
		return bool;
	}
	
	/** 
	 * @Title: selectUser
	 * @Description: 用戶管理-查找用户-分页查询
	 * @return PageInfo<User>
	 */
	@Override
	public PageInfo<User> selectUser(PageVo pageVo) {
			Integer pageNum = pageVo.getPage();
			Integer pageSize = pageVo.getLimit();
			PageHelper.startPage(pageNum, pageSize);
			String Key = pageVo.getKey();
		 	System.out.println(Key);
			List<User> user1 = userDao.selectAll(Key);
			PageInfo<User> pageInfo= new PageInfo<User>(user1);
			return pageInfo;
	}
	
	/** 
	 * @Title: updateUser1
	 * @Description: 用戶管理-修改用户
	 * @return Result
	 */
	@Override
	public User updateUser1 (User user) {
			Integer id = user.getId();
			User user1 = userDao.selectByid(id);
			return user1;
	}
	
	/** 
	 * @Title: updateUser
	 * @Description: 用戶管理-修改用户
	 * @return Result
	 */
	@Override
	public Boolean updateUser (User user) {
			Boolean bool = false;
			User user1 = new User();
			user1.setUserName(user.getUserName());
			user1.setPassword(user.getPassword());
			user1.setPhone(user.getPhone());
			user1.setSex(user.getSex());
			user1.setStatus(user.getStatus());
			user1.setEmail(user.getEmail());
			user1.setId(user.getId());
			Integer a = userDao.update(user);
			if(a>0) {
				bool = true;
			}
			return bool;
	}
}