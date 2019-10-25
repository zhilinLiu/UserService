package com.kando.entity;

import java.util.Date;

/**  
* @ClassName: Organization  
* @Description: TODO(单位表实体类)  
* @author 孙雨佳  
* @date 2019年10月24日  
*    
*/ 
public class Organization {
	private Integer id;
    private String name;
    private String org_id;
    private Date create_time;
    private Integer status;
    
    public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", org_id=" + org_id + ", create_time=" + create_time
				+ ", status=" + status + "]";
	}
}
