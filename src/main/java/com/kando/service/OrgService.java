package com.kando.service;


import com.github.pagehelper.PageInfo;
import com.kando.dto.Result;
import com.kando.entity.Organization;
import com.kando.entity.User;
import com.kando.vo.PageVo;

import java.util.Map;

public interface OrgService {
    PageInfo<Organization> selectOrg(PageVo pageVo);

	Boolean delete(Organization organization);
	
	Organization update(Organization organization);
	 
	Boolean update1(Organization organization);
	
	Boolean insertOrg(Organization organization);
    
}
