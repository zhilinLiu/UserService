package com.kando.entity;

import lombok.Data;

import java.util.Date;

/**  
* @ClassName: Organization  
* @Description: TODO(单位表实体类)  
* @author 孙雨佳  
* @date 2019年10月24日  
*    
*/
@Data
public class Organization {
	private Integer id;
    private String name;
    private String orgId;
    private Integer status;
    private Date createTime;

}
