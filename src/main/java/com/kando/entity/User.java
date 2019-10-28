package com.kando.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

/**  
* @ClassName: User  
* @Description: TODO(用户表实体类)  
* @author 孙雨佳  
* @date 2019年10月24日  
*    
*/
@Data
public class User implements Serializable {
    private Integer id;

    private String userName;
    private String password;

    private Integer sex;
    private String email;
    private String phone;
    
	private Date createTime;
    
    private Integer status;
    
    private String role;
    
    private String seccode;

}