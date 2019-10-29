package com.kando.service;


import com.github.pagehelper.PageInfo;
import com.kando.common.exception.ResultEnum;
import com.kando.dto.Result;
import com.kando.entity.Organization;
import com.kando.entity.User;
import com.kando.vo.PageVo;

import java.util.Map;

public interface OrgService {
	PageInfo<Organization> selectOrg(PageVo pageVo);

	ResultEnum deleteOrg(Organization organization);

	Organization updateOrg(Organization organization);

	ResultEnum updateOrg1(Organization organization);

	ResultEnum insertOrg(Organization organization);

}
