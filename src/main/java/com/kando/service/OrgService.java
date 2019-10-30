package com.kando.service;


import com.github.pagehelper.PageInfo;
import com.kando.common.exception.ResultEnum;
import com.kando.dto.Result;
import com.kando.entity.Organization;
import com.kando.entity.User;
import com.kando.vo.PageVo;

import java.util.Map;

public interface OrgService {
	//分页查询单位
	PageInfo<Organization> selectOrg(PageVo pageVo);
	//删除单位
	ResultEnum deleteOrg(Organization organization);
	//修改单位-返回organization
	Organization updateOrg(Organization organization);
	//修改成功
	ResultEnum updateOrg1(Organization organization);
	//新增单位
	ResultEnum insertOrg(Organization organization);

}
