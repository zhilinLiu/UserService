package com.kando.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kando.dao.OrgDao;
import com.kando.entity.Organization;
import com.kando.service.OrgService;
import com.kando.service.UserService;
import com.kando.util.TestDate;
import com.kando.vo.PageVo;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class OrgServiceImpl implements OrgService {
    @Autowired(required = true)
    private OrgDao orgDao;
    
        
	@Override
	public PageInfo<Organization> selectOrg(PageVo pageVo) {
			String Key = pageVo.getKey();
			System.out.println(Key);
			Integer pageNum = pageVo.getPage();
			Integer pageSize = pageVo.getLimit();
			PageHelper.startPage(pageNum, pageSize);
			List<Organization> organization = orgDao.selectAll(Key);
			PageInfo<Organization> pageInfo= new PageInfo<Organization>(organization);
			return pageInfo;
	}
	/** 
	 * @Title: delete
	 * @Description: 单位管理-刪除单位
	 * @param map
	 * @return map
	 * @see UserService#delete(Map)
	 */
	@Override
	public Boolean delete(Organization organization) {
			Integer id  = organization.getId();
			Boolean bool = false;
			if(ObjectUtils.isNotEmpty(orgDao.selectByid(id))){
					orgDao.deleteByid(Integer.valueOf(id));
					bool = true;
			}
			return bool;

	}
	/** 
	 * @Title: update
	 * @Description: 单位管理-修改单位
	 * @param map
	 * @return map
	 * @see UserService#selete(Map)
	 */
	@Override
	public Organization update (Organization organization) {
			Integer id = organization.getId();
			Organization organization1 = orgDao.selectByid(id);
			return organization;
	}
	
	/** 
	 * @Title: update
	 * @Description:单位管理-修改单位
	 * @param map
	 * @return map
	 * @see UserService#selete(Map)
	 */
	@Override
	public Boolean update1 (Organization organization) {
			Boolean bool = false;
			Organization organization1 = new Organization();
			organization1.setId(organization.getId());
			organization1.setName(organization.getName());
			organization1.setOrg_id(organization.getOrg_id());
			organization1.setStatus(organization.getStatus());
			orgDao.update(organization);
			return bool;
	}
	/** 
	 * @Title: insert
	 * @Description:单位管理-新增单位
	 * @param map
	 * @return map
	 * @see UserService#insert(Map)
	 */
	@Override
	public Boolean insertOrg (Organization organization) {
		Boolean bool= false;
		TestDate date = new TestDate();
		Organization organization1 = new Organization();
		organization1.setCreate_time(date.getDate());
		organization1.setName(organization.getName());
		organization1.setOrg_id(organization.getOrg_id());
		organization1.setStatus(organization.getStatus());
		if(orgDao.insertOrg(organization)>0) {
			bool = true;
		}
		return bool;
	}
}
