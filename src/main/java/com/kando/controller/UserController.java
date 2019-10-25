package com.kando.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kando.service.UserService;

/**  
* @ClassName: UserController  
* @Description: 控制器类-后端接口
* @author 孙雨佳  
* @date 2019年10月18日  
*    
*/ 
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestBody Map<String, String> map) {
		return userService.login(map);
	}

	@CrossOrigin
	@RequestMapping(value = "/login2", method = RequestMethod.POST)
	public Map<String, Object> login2(@RequestBody Map<String, String> map) {
		return userService.login2(map);
	}

	@CrossOrigin
	@RequestMapping(value = "/login3", method = RequestMethod.POST)
	public Map<String, Object> login3(@RequestBody Map<String, String> map) {
		return userService.login3(map);
	}

	@CrossOrigin
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public Map<String, Object> index(@RequestBody Map<String, String> map) {
		return userService.index(map);
	}

	@CrossOrigin
	@RequestMapping(value = "/index1", method = RequestMethod.POST)
	public Map<String, Object> index1(@RequestBody Map<String, String> map) {
		return userService.index1(map);
	}

	@CrossOrigin
	@RequestMapping(value = "/index2", method = RequestMethod.POST)
	public Map<String, Object> index2(@RequestBody Map<String, String> map) {
		return userService.index2(map);
	}

	@CrossOrigin
	@RequestMapping(value = "/index3", method = RequestMethod.POST)
	public Map<String, Object> index3(@RequestBody Map<String, String> map) {
		return userService.index3(map);
	}
}