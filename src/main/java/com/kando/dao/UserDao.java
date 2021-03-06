package com.kando.dao;

import java.util.List;

import com.kando.entity.User;
import org.apache.ibatis.annotations.Param;

public interface  UserDao {
	 User selectByphone(@Param("phone") String phone);
	
	 User login(@Param("phone") String phone, @Param("password") String password);

	 Boolean index(User user);
	
	 User selectByemail(@Param("email") String email);
	
	 Boolean updateEmail(User user);

	 Boolean update(User user);

	 Boolean deleteByid(@Param("id") Integer id);
	
	 User selectByid(@Param("id") Integer id);

	 List<User> selectAll(@Param("key") String key);
	
	
}