package com.kando.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kando.common.exception.MeioException;
import com.kando.common.exception.ResultEnum;
import com.kando.dao.OrgDao;
import com.kando.entity.Organization;
import com.kando.service.OrgService;
import com.kando.service.UserService;
import com.kando.util.TestDate;
import com.kando.vo.PageVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgDao orgDao;

    /**
     * @return PageInfo
     * @Title: selectOrg
     * @Description: 单位管理-模糊查询-查询单位
     */
    @Override
    public PageInfo<Organization> selectOrg(PageVo pageVo) {
        String Key = pageVo.getKey();
        System.out.println(Key);
        Integer pageNum = pageVo.getPage();
        Integer pageSize = pageVo.getLimit();
        if (ObjectUtils.isEmpty(pageNum) || ObjectUtils.isEmpty(pageSize)) {
            //页码，条数不能为空，报参数异常
            throw new MeioException(ResultEnum.PARAM_ERROR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Organization> organization = orgDao.selectAll(Key);
        organization.stream().forEach(x -> System.out.println(x));
        PageInfo<Organization> pageInfo = new PageInfo<Organization>(organization);
        return pageInfo;
    }

    /**
     * @return ResultEnum
     * @Title: delete
     * @Description: 单位管理-刪除单位
     */
    @Override
    public ResultEnum deleteOrg(Organization organization) {
        Integer id = organization.getId();
        if (ObjectUtils.isEmpty(id)) {
            /*单位ID不存在*/
            throw new MeioException(ResultEnum.UNIT_ID_IS_NOT_EXIST_ERROR);
        }
        if (ObjectUtils.isEmpty(orgDao.selectByid(id))) {
            /*单位不存在*/
            throw new MeioException(ResultEnum.UNIT_IS_NOT_EXIST_ERROR);
        }
        Integer a = orgDao.deleteByid(id);
        if (a <= 0) throw new MeioException(ResultEnum.DELETE_UNIT_ERROR);
        return ResultEnum.SUCCESS;
    }

    /**
     * @return Organization
     * @Title: update
     * @Description: 单位管理-修改单位-点击修改
     */
    @Override
    public Organization updateOrg(Organization organization) {
        Integer id = organization.getId();
        if (ObjectUtils.isEmpty(id)) {
            /*单位ID不存在*/
            throw new MeioException(ResultEnum.UNIT_ID_IS_NOT_EXIST_ERROR);
        }
        Organization organization1 = orgDao.selectByid(id);
        if (ObjectUtils.isEmpty(organization1)) {
            /*单位不存在*/
            throw new MeioException(ResultEnum.UNIT_IS_NOT_EXIST_ERROR);
        }
        return organization1;
    }

    /**
     * @return ResultEnum
     * @Title: update
     * @Description:单位管理-修改单位-确认修改
     */
    @Override
    public ResultEnum updateOrg1(Organization organization) {
        Organization organization1 = new Organization();
        organization1.setId(organization.getId());
        organization1.setName(organization.getName());
        organization1.setOrgId(organization.getOrgId());
        organization1.setStatus(organization.getStatus());
        orgDao.update(organization);
        return ResultEnum.SUCCESS;
    }

    /**
     * @return ResultEnum
     * @Title: insert
     * @Description:单位管理-新增单位
     */
    @Override
    public ResultEnum insertOrg(Organization organization) {
        TestDate date = new TestDate();
        Organization organization1 = new Organization();
        String name = organization.getName();
        String orgId = organization.getOrgId();
        Integer status = organization.getStatus();
        if (StringUtils.isBlank(name) || StringUtils.isBlank(orgId) || ObjectUtils.isEmpty(status)) {
            //参数不能为空，报参数异常
            throw new MeioException(ResultEnum.PARAM_ERROR);
        }
        organization1.setCreateTime(date.getDate());
        organization1.setName(name);
        organization1.setOrgId(orgId);
        organization1.setStatus(status);
        Integer a = orgDao.insertOrg(organization);
        //新增单位失败
        if (a <= 0) throw new MeioException(ResultEnum.INSERT_UNIT_ERROR);
        return ResultEnum.SUCCESS;
    }
}
