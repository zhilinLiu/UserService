package com.kando.service;

import com.kando.entity.Role;

/**
 *
 */
public interface RoleAuthService {
    //增加角色权限
    boolean insertRoleAuth(Integer roleId,Integer authId);
}
