package com.kando.service;

import com.kando.entity.Role;

import java.util.List;

/**
 *
 */
public interface UserRoleService {
    //根据用户id查询所有的角色
    List<Role> selectRoleId(Integer userId);

}
