package com.kando.dao;

import com.kando.entity.Organization;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrgDao {

	public Organization selectByid(@Param("id") Integer id);
		
	public List<Organization> selectAll(@Param("key") String key);

	public Integer deleteByid(@Param("id") Integer id);
	
	public Integer update(Organization organization);
	
	public Integer insertOrg(Organization organization);
}
