package com.kando.controller;

import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
* @ClassName: UserController  
* @Description: 控制器类-登陆注册-用户管理
* @author 孙雨佳  
* @date 2019年10月18日  
*    
*/ 
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	/**  
	* @Title: loginByPwd  
	* @Description: TODO(登陆操作-用户名密码)  
	* @param  map  参数  
	* @return Map<String,Object>    返回类型  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/loginByPwd", method = RequestMethod.POST)
	public Result loginByPwd(@RequestBody User user) {
		userService.loginByPwd(user);
		Result result = new Result();
		result.setCode(0);
		result.setMessage("登陆成功");
		result.setSuccess(true);
		return result;
	}

	/**  
	* @Title: loginByCode  
	* @Description: TODO(登陆操作-发送手机验证码)  
	* @param  map 参数  
	* @return Map<String,Object>    返回类型  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/loginByCode", method = RequestMethod.POST)
	public Result loginByCode(@RequestBody @Validated User user) {
		Result result = new Result();
		result.setCode(0);
		result.setMessage("验证码发送成功");
		result.setSuccess(userService.loginByCode(user));
		return result;
	}

	/**  
	* @Title: loginCheckCode  
	* @Description: TODO(登陆操作-验证验证码)  
	* @param   map 参数  
	* @return Map<String,Object>    返回类型  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/loginCheckCode", method = RequestMethod.POST)
	public Result checkCode(@RequestBody @Validated User user) {
		Result result = new Result();
		result.setCode(0);
		result.setMessage("登陆成功");
		result.setSuccess(userService.loginCheckCode(user));
		return result;
	}

	/**  
	* @Title: indexByCode  
	* @Description: TODO(注册操作-发送手机验证码)  
	* @param   参数  
	* @return Map<String,Object>    返回类型  
	* @throws  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/indexByCode", method = RequestMethod.GET)
	public Result indexByCode(String phone) {
		Result result = new Result();
		System.out.println(phone);
		User user = new User();
		user.setPhone(phone);
		userService.indexByCode(user);
		result.setCode(0);
		result.setMessage("发送验证码成功");
		result.setSuccess(true);
		return result;
	}

	/**  
	* @Title: indexCheckCode  
	* @Description: TODO(注册操作-验证手机验证码)  
	* @param   参数  
	* @return Map<String,Object>    返回类型  
	* @throws  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/indexCheckCode", method = RequestMethod.POST)
	public Result indexCheckCode(@RequestBody  User user) {
		Result result = new Result();
		result.setCode(0);
		result.setMessage("注册成功");
		result.setSuccess(userService.indexCheckCode(user));
		return result;
	}

	/**  
	* @Title: indexBindEmail  
	* @Description: TODO(注册操作-绑定邮箱-发送邮箱验证码)  
	* @param   参数  
	* @return Map<String,Object>    返回类型  
	* @throws  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/indexBindEmail", method = RequestMethod.POST)
	public Result indexBindEmail(@RequestBody  User user) {
		return userService.indexBindEmail(user);
	}

	/**  
	* @Title: IndexEmailCode  
	* @Description: TODO(注册操作-绑定邮箱-验证邮箱验证码)  
	* @param   参数  
	* @return Map<String,Object>    返回类型  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/IndexEmailCode", method = RequestMethod.POST)
	public Result IndexEmailCode(@RequestBody  User user) {
		Result result = new Result();
		if(userService.IndexEmailCode(user)) {
			result.setCode(0);
			result.setMessage("绑定成功");
			result.setSuccess(true);
		}else {
			result.setCode(1);
			result.setMessage("绑定失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	/**  
	* @Title: selectUser  
	* @Description: TODO(查询用户)  
	* @param   参数  
	* @return PageInfo<User>    返回类型  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/selectUser", method = RequestMethod.POST)
	public  Result selectUser(@RequestBody PageVo pageVo) {
			Result result = new Result();
			PageInfo<User> pageInfo=userService.selectUser(pageVo);
			result.setCode(0);
	        result.setMessage("查询成功");
	        result.setSuccess(true);
	        result.setData(pageInfo);
		    return result;
	}
	
	/**  
	* @Title: deleteUser  
	* @Description: TODO(删除用户)  
	* @param   参数  
	* @return Map<String,Object>    返回类型  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public Result deleteUser(@RequestBody  User user) {
		Result result = new Result();
		if(userService.deleteUser(user)){
			result.setCode(0);
			result.setMessage("删除成功");
			result.setSuccess(true);
		}else {
			result.setCode(1);
			result.setMessage("删除失败");
			result.setSuccess(false);
		}
		return result;
	}
	
	/**  
	* @Title: updateUser  
	* @Description: TODO(修改用户)  
	* @param   参数  
	* @return Map<String,Object>    返回类型  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public Result updateUser(@RequestBody  User user) {	
		Result result = new Result();
		User user1 = userService.updateUser1(user);
		result.setCode(0);
		result.setData(user1);
		result.setMessage("确认修改");
		return result;
		
	}
	
	/**  
	* @Title: updateUser1  
	* @Description: TODO()  
	* @param   参数  
	* @return Result    返回类型  
	* @throws  
	*/ 
	@CrossOrigin
	@RequestMapping(value = "/updateUser1", method = RequestMethod.POST)
	public Result updateUser1(@RequestBody  User user) {
			Result result = new Result();
			result.setCode(0);
			result.setMessage("修改成功");
			result.setSuccess(userService.updateUser(user));
			return result;
	}
}