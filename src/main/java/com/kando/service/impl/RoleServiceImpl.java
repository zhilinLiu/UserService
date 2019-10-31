package com.kando.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kando.ao.RoleAo;
import com.kando.dao.AuthorityDao;
import com.kando.dao.RoleAuthDao;
import com.kando.dto.QueryResult;
import com.kando.entity.Authority;
import com.kando.entity.Role;
import com.kando.dao.RoleDao;
import com.kando.entity.RoleAuth;
import com.kando.service.RoleService;
import com.kando.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleAuthDao roleAuthDao;
    @Autowired
    private RoleAuthServiceImpl roleAuthService;
    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public QueryResult queryAllRoles(PageVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getLimit());
        QueryResult<Role> listQueryResult = new QueryResult<>();
        if (vo.getKey() == null || vo.getKey().equals("")) {
            List<Role> list = roleDao.queryAllRoles();
            PageInfo<Role> pageInfo = new PageInfo<>(list);
            listQueryResult.setPageNo(vo.getPage());
            listQueryResult.setRows(pageInfo.getList());
            listQueryResult.setTotalRows(pageInfo.getTotal());
            listQueryResult.setPageSize(vo.getLimit());
        } else {
            String str = "%" + vo.getKey() + "%";
            List<Role> list = roleDao.queryAllRolesLike(str);
            PageInfo<Role> pageInfo = new PageInfo<>(list);
            listQueryResult.setPageNo(vo.getPage());
            listQueryResult.setRows(pageInfo.getList());
            listQueryResult.setTotalRows(pageInfo.getTotal());
            listQueryResult.setPageSize(vo.getLimit());
        }
        return listQueryResult;
    }


    @Override
    @Transactional
    public boolean inserRole(RoleAo role1) {
        //获取需要添加的权限id
        List<Integer> authId = role1.getAuthId();
        //新建一个角色
        Role role = new Role();
        //将前端传的值赋予新建的角色
        BeanUtils.copyProperties(role1, role);
        if (!StringUtils.isNotEmpty(role.getCreateUserId())) {
            //赋予默认值
            role.setCreateUserId("default");
        }
        //插入角色
        boolean flag = false;
        if (roleDao.inserRole(role)) {
            //得到刚插入的角色的ID
            Integer id = role.getId();
            //往角色-权限中间表插入权限
            try {
                authId.forEach(x -> roleAuthService.insertRoleAuth(id, x));
                flag = true;
            } catch (Exception e) {
                log.error("给角色插入权限的时候失败了");
                throw e;
            }
        }
        return flag;
    }

    @Override
    public boolean deleteRole(Integer id) {
        roleAuthService.deleteAllAuth(id);
        return roleDao.deleteRole(id);
    }

    @Override
    @Transactional
    public boolean updateRole(RoleAo role1) {
        List<Integer> authId = role1.getAuthId();
        Role role = new Role();
        BeanUtils.copyProperties(role1, role);
        boolean flag = false;
        if (roleDao.updateRole(role)) {
            //删除该角色下的所有权限
            roleAuthService.deleteAllAuth(role.getId());
            //赋予角色修改的权限
            authId.forEach(x -> roleAuthService.insertRoleAuth(role.getId(), x));
            flag = true;
        }
        return flag;
    }

    @Override
    public Role selectRole(Integer id) {
        //根据角色id查出对应的权限
        List<RoleAuth> roleAuths = roleAuthDao.selectAuth(id);
        List<Authority> list = new ArrayList<>();
        roleAuths.forEach(x -> {
            //根据中间表拥有的权限id依次查询该权限信息
            list.add(authorityDao.selectOne(x.getAuthId()));
        });
        Role role = roleDao.selectRole(id);
        if (role != null) {
            role.setAuthority(list);
        }
        return role;

    }

    @Override
    public List<Role> all() {
        return roleDao.queryAllRoles();
    }


}
