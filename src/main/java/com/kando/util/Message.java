package com.kando.util;



public class Message {

	public static final int PASSWORD_IS_BLANK = 0;//用户名密码不能为空
	public static final int LOGIN_SUCCESS = 1;//登陆成功
	public static final int USERNAME_IS_NOT_BLANK = 2;//用户不存在
	public static final int LOGIN_FAIL = 3;//用户名或密码不正确
	
	public static final int REGISTE_FAIL = 0;//用户名密码不能为空
	public static final int REGISTE_SUCCESS = 1;//注册成功
	public static final int USERNAME_IS_BLANK = 2;//用户已存在
	public static final int MALFORMED = 3;//不满足正则
	public static final int SECCODE_FAIL = 4;//验证码错误
	

}
