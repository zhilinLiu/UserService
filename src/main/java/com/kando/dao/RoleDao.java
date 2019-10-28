package com.kando.dao;

import com.kando.entity.Role;

import java.util.List;

public interface RoleDao {



    //查询所有角色
    List<Role> queryAllRoles();

    List<Role> queryAllRolesLike(String fied);

    //新增角色
    boolean inserRole(Role role);
    //根据ID删除角色
    boolean deleteRole(Integer id);

    //根据ID更新角色
    boolean updateRole(Role role);

    //根据角色ID查询角色信息
    Role selectRole(Integer id);




}
