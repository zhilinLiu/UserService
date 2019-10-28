package com.kando.controller;

import com.github.pagehelper.PageInfo;
import com.kando.dto.Result;
import com.kando.entity.Organization;
import com.kando.service.impl.OrgServiceImpl;
import com.kando.vo.PageVo;

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
    * @param  map  参数  
    * @return PageInfo<Organization>    返回类型  
    * @throws  
    */ 
    
	@RequestMapping(value = "/selectOrg", method = RequestMethod.POST)
	public  Result selectOrg(@RequestBody PageVo pageVo) {
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
    * @param  map  参数  
    * @return Map<String,Object>    返回类型  
    * @throws  
    */ 
    
	@RequestMapping(value = "/deleteOrg", method = RequestMethod.POST)
	public  Result deleteOrg(@RequestBody Organization organization) {
    	Result result = new Result();
    	if(orgService.delete(organization)) {
    		result.setCode(0);
    		result.setMessage("删除成功");
    		result.setSuccess(true);
    	}else {
    		result.setCode(1);
    		result.setMessage("删除失败");
    		result.setSuccess(false);
    	}
    	return result;
	}
    
    /**  
    * @Title: updateOrg1  
    * @Description: TODO(修改单位-点击修改)  
    * @param map  参数  
    * @return Organization   返回类型  
    * @throws  
    */ 
    
	@RequestMapping(value = "/updateOrg", method = RequestMethod.POST)
	public  Result updateOrg(@RequestBody Organization organization) {
    	Result result = new Result();
		result.setCode(0);
		result.setData(orgService.update(organization));
		result.setMessage("点击修改");
		result.setSuccess(true);
		return result;
	}
    
    /**  
    * @Title: updateOrg  
    * @Description: TODO(修改单位-确认修改)  
    * @param  map 参数  
    * @return Map<String, Object>    返回类型  
    * @throws  
    */ 
    
	@RequestMapping(value = "/updateOrg1", method = RequestMethod.POST)
	public  Result updateOrg1(@RequestBody Organization organization) {
    	Result result = new Result();
    	orgService.update(organization);
		result.setCode(0);
		result.setSuccess(true);
		result.setMessage("修改成功");
		return result;
	}
    
    /**  
    * @Title: insertOrg  
    * @Description: TODO(单位管理-新增单位)  
    * @param  map  参数  
    * @return Map<String,Object>   返回类型  
    * @throws  
    */ 
    
	@RequestMapping(value = "/insertOrg", method = RequestMethod.POST)
	public  Result insertOrg(@RequestBody Organization organization) {
    	Result result = new Result();
		orgService.insertOrg(organization);
		result.setCode(0);
		result.setSuccess(true);
		result.setMessage("修改成功");
		return result;
	}
    
}
