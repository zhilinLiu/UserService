package com.kando.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kando.dto.QueryResult;
import com.kando.entity.Role;
import com.kando.dao.RoleDao;
import com.kando.service.RoleService;
import com.kando.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;


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
        return roleDao.selectRole(id);
    }


}
