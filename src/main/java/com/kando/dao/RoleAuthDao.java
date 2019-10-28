package com.kando.dao;

import com.kando.entity.RoleAuth;

import java.util.List;

/**
 * 角色权限中间表操作
 */
public interface RoleAuthDao {
    //查询角色对应权限
    List<RoleAuth> selectAuth(Integer roleId);
}
