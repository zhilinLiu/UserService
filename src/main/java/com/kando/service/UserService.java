package com.kando.service;


import java.util.Map;




/**  
* @ClassName: UserService  
* @Description: 业务层接口
* @author 孙雨佳  
* @date 2019年10月18日  
*    
*/ 
public interface UserService {
	
	     Map<String, Object> index(Map<String, String> map);
	   
	     Map<String, Object> index1(Map<String, String> map);
	    
	     Map<String, Object> index2(Map<String, String> map);
		   
	     Map<String, Object> index3(Map<String, String> map);
	    
	     Map<String, Object> login(Map<String, String> map);
	    
	     Map<String, Object> login2(Map<String, String> map);
	    
	     Map<String, Object> login3(Map<String, String> map);
}