package com.kando.dao;

import com.kando.entity.RoleAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限中间表操作
 */
public interface RoleAuthDao {
    //查询角色对应权限
    List<RoleAuth> selectAuth(Integer roleId);
    //增加角色权限
    boolean insertRoleAuth(@Param("roleId") Integer roleId,@Param("authId") Integer authId);

    boolean deleteRoleAuth(@Param("roleId") Integer roleId,@Param("authId") Integer authId);
    //删除该角色下的所有权限
    boolean deleteAllAuth(@Param("roleId") Integer roleId);

    boolean updateRoleAuth(@Param("roleId") Integer roleId,@Param("authId") Integer authId,@Param("newAuthId")Integer newId);
}
