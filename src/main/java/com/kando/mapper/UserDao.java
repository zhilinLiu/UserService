package com.kando.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kando.entity.User;
@Mapper
public interface  UserDao {
	public User selectByphone(@Param("phone") String phone);
	
	public User login(@Param("phone") String phone, @Param("password") String password);
	
	public Integer index(User user);
	
	public User selectByemail(@Param("email") String email);
	
	public Integer updateEmail(User user);
}