package com.kando.service;


import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.kando.dto.Result;
import com.kando.entity.User;
import com.kando.vo.PageVo;





/**  
* @ClassName: UserService  
* @Description: 业务层接口
* @author 孙雨佳  
* @date 2019年10月23日  
*    
*/ 
public interface UserService {
	
		 Boolean loginByPwd(User user);
	   
	     Boolean loginByCode(User user);
	    
	     Boolean loginCheckCode(User user);
		   
	     Boolean indexByCode(User user);
	    
	     Boolean indexCheckCode(User user);
	    
	     Result<?> indexBindEmail(User user);
	    
	     Boolean IndexEmailCode(User user);

	     PageInfo<User> selectUser(PageVo pageVo);
		 
	     Boolean deleteUser(User user);
		                                          
		 Boolean updateUser(User user);
		 
		 User updateUser1(User user);

}