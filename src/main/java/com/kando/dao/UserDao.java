package com.kando.dao;

import java.util.List;

import com.kando.entity.User;
import org.apache.ibatis.annotations.Param;

public interface  UserDao {
	public User selectByphone(@Param("phone") String phone);
	
	public User login(@Param("phone") String phone, @Param("password") String password);
	
	public Integer index(User user);
	
	public User selectByemail(@Param("email") String email);
	
	public Integer updateEmail(User user);
	
	public Integer update(User user);
	
	public Integer deleteByid(@Param("id") Integer id);
	
	public User selectByid(@Param("id") Integer id);

	public List<User> selectAll(@Param("key") String key);
	
	
}