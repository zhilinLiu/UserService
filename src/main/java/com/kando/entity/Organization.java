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

	private Integer id;//单位ID

    private String name;//单位名称

    private String orgId;//上级单位ID

    private Integer status;//状态

    private Date createTime;//创建时间

}
