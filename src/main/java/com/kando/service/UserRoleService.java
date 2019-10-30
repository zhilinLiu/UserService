package com.kando.service;

import com.kando.common.exception.ResultEnum;
import com.kando.entity.Role;

import java.util.List;

/**
 *
 */
public interface UserRoleService {
    //根据用户id查询所有的角色id
    List<Role> selectRoleId(Integer userId);
    //根据用户id删除所有的角色id
    Boolean deleteRoleId(Integer userId);
    //新增用户角色
    Boolean insertRoleId(Integer userId,Integer roleId);

}
