package com.kando.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import com.kando.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.kando.entity.User;
import com.kando.mapper.UserDao;
import com.kando.util.Message;
import com.kando.util.Random;
import com.kando.util.TestDate;

/**  
* @ClassName: UserServiceImpl  
* @Description: TODO业务层
* @author 孙雨佳  
* @date 2019年10月18日  
*    
*/ 
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleServiceImpl authorityService;

	@Autowired
	private StringRedisTemplate redis;
	
	/** 
	 * @Title: login 
	 * @Description:登陆操作-手机号密码登陆
	 * @param map
	 * @return map
	 * @see UserService#login(Map)
	 */
	@Override
	public Map<String, Object> login(Map<String, String> map) {
		try {
			String password = map.get("password");
			String phone = map.get("phone");
			Map<String, Object> result = new HashMap<String, Object>();
			// 用户名密码不为空
			if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(password)) {
				// 结果集不为空
				if (ObjectUtils.isNotEmpty(userDao.selectByphone(phone))) {
					// 开始验证密码
					// 获取钥匙
					UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(phone, password);
					// 获取大门
					Subject subject = SecurityUtils.getSubject();
					try {
						// 开门
						subject.login(usernamePasswordToken);
						result.put("message", "登陆成功");
						result.put("resultCode", Message.LOGIN_SUCCESS);
					} catch (Exception exception) {
						result.put("message", "手机号或密码不正确");
						result.put("resultCode", Message.LOGIN_FAIL);
						return result;
					}
				} else {
					result.put("message", "手机号不存在");
					result.put("resultCode", Message.USERNAME_IS_NOT_BLANK);
				}
			} else {
				result.put("message", "手机号或密码不能为空");
				result.put("resultCode", Message.PASSWORD_IS_BLANK);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/** 
	 * @Title: login2 
	 * @Description: 登陆操作-手机短信登陆-发送验证码
	 * @param map
	 * @return 
	 * @see UserService#login2(Map)
	 */
	@Override
	public Map<String, Object> login2(Map<String, String> map) {
		try {
			String seccode = Random.getRandom();
			String phone = map.get("phone");
			redis.opsForValue().set(phone, seccode, 5, TimeUnit.MINUTES);
			Map<String, Object> result = new HashMap<String, Object>();
			System.out.println("登陆");
			if (StringUtils.isNotBlank(phone)) {
				if (ObjectUtils.isNotEmpty(userDao.selectByphone(phone))) {
					result.put("message", "验证码发送成功");
					result.put("seccode", seccode);
					result.put("resultCode", Message.LOGIN_SUCCESS);
				} else {
					result.put("message", "手机号不存在");
					result.put("resultCode", Message.USERNAME_IS_NOT_BLANK);
				}
			} else {
				result.put("message", "手机号不能为空");
				result.put("resultCode", Message.PASSWORD_IS_BLANK);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 
	 * @Title: login3 
	 * @Description: 登陆操作-手机短信登陆-验证验证码
	 * @param map
	 * @return map
	 * @see UserService#login3(Map)
	 */
	@Override
	public Map<String, Object> login3(Map<String, String> map) {
		try {
            String phone = map.get("phone");
            String seccode1 = redis.opsForValue().get(phone);
            String seccode = map.get("seccode");
            Map<String, Object> result = new HashMap<String, Object>();
            System.out.println("登陆");
            if (StringUtils.isNotBlank(seccode1)) {
                if (seccode.equals(seccode1)) {
                    User user = userDao.selectByphone(phone);
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(phone, user.getPassword());
                    subject.login(usernamePasswordToken);
                    subject.getSession().setAttribute("user",user);
                    result.put("message", "登陆成功");
                    result.put("resultCode", Message.LOGIN_SUCCESS);
                } else {
                    result.put("message", "登陆失败，手机号或验证码不正确");
                    result.put("resultCode", Message.SECCODE_FAIL);
                }
            } else {
                result.put("message", "登陆失败，手机号或验证码不正确");
                result.put("resultCode", Message.SECCODE_FAIL);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	/** 
	 * @Title: index 
	 * @Description: 注册操作-发送手机验证码
	 * @param map
	 * @return map
	 * @see UserService#index(Map)
	 */
	@Override
	public Map<String, Object> index(Map<String, String> map) {
		try {
			String seccode = Random.getRandom();
			String phone = map.get("phone");
			redis.opsForValue().set(phone, seccode, 5, TimeUnit.MINUTES);
			Map<String, Object> result = new HashMap<String, Object>();
			System.out.println("注册");
			if (StringUtils.isNotBlank(phone)) {
				if (ObjectUtils.isEmpty(userDao.selectByphone(phone))) {
					result.put("message", "验证码发送成功");
					result.put("seccode", seccode);
					result.put("resultCode", Message.LOGIN_SUCCESS);
				} else {
					result.put("message", "手机号已存在");
					result.put("resultCode", Message.USERNAME_IS_NOT_BLANK);
				}
			} else {
				result.put("message", "手机号不能为空");
				result.put("resultCode", Message.PASSWORD_IS_BLANK);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	/** 
	 * @Title: index1 
	 * @Description: 注册操作-验证手机验证码
	 * @param map
	 * @return map
	 * @see UserService#index1(Map)
	 */
	@Override
	public Map<String, Object> index1(Map<String, String> map) {
		try {
			String phone = map.get("phone");
			String seccode1 = redis.opsForValue().get(phone);
			String seccode = map.get("seccode");
			String user_name = map.get("user_name");
			String password = map.get("password");
			String role = String.valueOf(map.get("role"));
			Integer sex = Integer.valueOf(map.get("sex"));
			Map<String, Object> result = new HashMap<String, Object>();
			System.out.println("验证验证码");
			if (StringUtils.isNotBlank(seccode1)) {
				if (seccode.equals(seccode1)) {
					User user = new User();
					user.setPhone(phone);
					user.setSex(sex);
					user.setUser_name(user_name);
					user.setPassword(password);
					TestDate date = new TestDate();
					user.setCreate_time(date.getDate());
					userDao.index(user);
					//添加默认角色--访客

					result.put("message", "注册成功");
					result.put("resultCode", Message.LOGIN_SUCCESS);
				} else {
					result.put("message", "注册失败，手机号或验证码不正确");
					result.put("resultCode", Message.SECCODE_FAIL);
				}
			} else {
				result.put("message", "注册失败，验证码不能为空");
				result.put("resultCode", Message.SECCODE_FAIL);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 
	 * @Title: index2 
	 * @Description: 注册操作-绑定邮箱-发送邮箱验证码
	 * @param map
	 * @return 
	 * @see UserService#index2(Map)
	 */
	@Override
	public Map<String, Object> index2(Map<String, String> map) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			String email = map.get("email");
			if (StringUtils.isNotBlank(email)) {
				if (ObjectUtils.isEmpty(userDao.selectByemail(email))) {
					String seccode = Random.getRandom();
					redis.opsForValue().set(email, seccode, 5, TimeUnit.MINUTES);
					result.put("message", "验证码发送成功");
					result.put("seccode", seccode);
					result.put("resultCode", Message.LOGIN_SUCCESS);
				} else {
					result.put("message", "邮箱已被绑定");
					result.put("resultCode", Message.USERNAME_IS_NOT_BLANK);
				}
			} else {
				result.put("message", "邮箱不能为空");
				result.put("resultCode", Message.PASSWORD_IS_BLANK);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 
	 * @Title: index3 
	 * @Description: 注册操作-验证邮箱验证码
	 * @param map
	 * @return map
	 * @see UserService#index3(Map)
	 */
	@Override
	public Map<String, Object> index3(Map<String, String> map) {
		try {
			String phone = map.get("phone");
			String email = map.get("email");
			String seccode1 = redis.opsForValue().get(email);
			String seccode = map.get("seccode");
			System.out.println(email);
			Map<String, Object> result = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(seccode1)) {
				if (seccode.equals(seccode1)) {
					User user = new User();
					user.setPhone(phone);
					user.setEmail(email);
					userDao.updateEmail(user);
					result.put("message", "绑定邮箱成功");
					result.put("resultCode", Message.LOGIN_SUCCESS);
					return result;
				} else {
					result.put("message", "绑定邮箱失败，邮箱或验证码不正确");
					result.put("resultCode", Message.SECCODE_FAIL);
				}
			} else {
				result.put("message", "绑定邮箱失败，邮箱或验证码不能为空");
				result.put("resultCode", Message.SECCODE_FAIL);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}