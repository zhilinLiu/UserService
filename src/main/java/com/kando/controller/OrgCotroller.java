package com.kando.controller;

import com.github.pagehelper.PageInfo;
import com.kando.common.exception.MeioException;
import com.kando.common.exception.ResultEnum;
import com.kando.dto.Result;
import com.kando.entity.Organization;
import com.kando.service.impl.OrgServiceImpl;
import com.kando.vo.PageVo;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrgCotroller {

	@Autowired
	private OrgServiceImpl orgService;


	/**
	 * @Title: selectOrg
	 * @Description: TODO(单位管理-查询单位)
	 * @return PageInfo<Organization>    返回类型
	 */

	@RequestMapping(value = "/selectOrg", method = RequestMethod.GET)
	public  Result selectOrg(PageVo pageVo) {
		Result result = new Result();
		result.setCode(0);
		result.setData(orgService.selectOrg(pageVo));
        result.setMessage("查询成功");
		result.setSuccess(true);
		return result;
	}


	/**
	 * @Title: deleteOrg
	 * @Description: TODO(单位管理-删除单位)
	 * @return Map<String,Object>    返回类型
	 */

	@RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
	public  Result deleteOrg(@RequestBody Organization organization) {
		Result result = new Result();
		ResultEnum resultEnum = orgService.deleteOrg(organization);
		result.setCode(resultEnum.getCode());
		result.setMessage(resultEnum.getMessage());
		result.setSuccess(true);
		return result;
	}

	/**
	 * @Title: updateOrg1
	 * @Description: TODO(修改单位-点击修改)
	 * @return Organization   返回类型
	 */

	@RequestMapping(value = "/updateOrg", method = RequestMethod.GET)
	public  Result updateOrg(Organization organization) {
		Result result = new Result();
		result.setCode(0);
		result.setData(orgService.updateOrg(organization));
		result.setMessage("点击修改");
		result.setSuccess(true);
		return result;
	}

	/**
	 * @Title: updateOrg
	 * @Description: TODO(修改单位-确认修改)
	 * @return Map<String, Object>    返回类型
	 */

	@RequestMapping(value = "/updateOrg1", method = RequestMethod.POST)
	public  Result updateOrg1(@RequestBody Organization organization) {
		Result result = new Result();
		ResultEnum resultEnum = orgService.updateOrg1(organization);
		result.setCode(resultEnum.getCode());
		result.setSuccess(true);
		result.setMessage(resultEnum.getMessage());
		return result;
	}

	/**
	 * @Title: insertOrg
	 * @Description: TODO(单位管理-新增单位)
	 * @return Map<String,Object> 返回类型
	 */

	@RequestMapping(value = "/insertOrg", method = RequestMethod.POST)
	public  Result insertOrg(@RequestBody Organization organization) {
		Result result = new Result();
		ResultEnum resultEnum = orgService.insertOrg(organization);
		result.setCode(resultEnum.getCode());
		result.setSuccess(true);
		result.setMessage(resultEnum.getMessage());
		return result;
	}

}
