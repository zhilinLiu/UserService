package com.kando.service;

import com.github.pagehelper.Page;
import com.kando.ao.RoleAo;
import com.kando.dto.QueryResult;
import com.kando.entity.Role;
import com.kando.vo.PageVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import java.util.List;

public interface RoleService {

    //查询所有角色
    public QueryResult queryAllRoles(PageVo vo);

    boolean inserRole(RoleAo role);

    boolean deleteRole(Integer id);

    boolean updateRole(RoleAo role);

    Role selectRole(Integer id);
}
