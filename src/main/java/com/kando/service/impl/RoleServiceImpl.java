package com.kando.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kando.dao.AuthorityDao;
import com.kando.dao.RoleAuthDao;
import com.kando.dto.QueryResult;
import com.kando.entity.Authority;
import com.kando.entity.Role;
import com.kando.dao.RoleDao;
import com.kando.entity.RoleAuth;
import com.kando.service.RoleService;
import com.kando.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleAuthDao roleAuthDao;
    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public QueryResult queryAllRoles(PageVo vo) {
        PageHelper.startPage(vo.getPage(),vo.getLimit());
        QueryResult<Role> listQueryResult = new QueryResult<>();
        if(vo.getKey()==null||vo.getKey().equals("")){
            List<Role> list = roleDao.queryAllRoles();
            PageInfo<Role> pageInfo = new PageInfo<>(list);
            listQueryResult.setPageNo(vo.getPage());
            listQueryResult.setRows(pageInfo.getList());
            listQueryResult.setTotalRows(pageInfo.getTotal());
            listQueryResult.setPageSize(vo.getLimit());
        }else {
            String str = "%"+vo.getKey()+"%";
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
    public boolean inserRole(Role role) {
        if(!StringUtils.isNotEmpty(role.getCreateUserId())){
            //赋予默认值
            role.setCreateUserId("default");
        }
        return roleDao.inserRole(role);
    }

    @Override
    public boolean deleteRole(Integer id) {
        return roleDao.deleteRole(id);
    }

    @Override
    public boolean updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public Role selectRole(Integer id) {
        //根据角色id查出对应的权限
        List<RoleAuth> roleAuths = roleAuthDao.selectAuth(id);
        List<Authority> list = new ArrayList<>();
        roleAuths.forEach(x->{
            //根据中间表拥有的权限id依次查询该权限信息
            list.add(authorityDao.selectOne(x.getAuthId()));
        });
        Role role = roleDao.selectRole(id);
        role.setAuthority(list);
        return role;

    }


}
