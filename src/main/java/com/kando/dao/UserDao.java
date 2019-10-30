package com.kando.dao;

import java.util.List;

import com.kando.entity.User;
import org.apache.ibatis.annotations.Param;

public interface  UserDao {
	 User selectByphone(@Param("phone") String phone);
	
	 User login(@Param("phone") String phone, @Param("password") String password);
	
	 Integer index(User user);
	
	 User selectByemail(@Param("email") String email);
	
	 Integer updateEmail(User user);
	
	 Integer update(User user);
	
	 Integer deleteByid(@Param("id") Integer id);
	
	 User selectByid(@Param("id") Integer id);

	 List<User> selectAll(@Param("key") String key);
	
	
}