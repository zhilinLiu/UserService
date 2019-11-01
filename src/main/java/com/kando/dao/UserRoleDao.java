package com.kando.dao;

import com.kando.common.exception.ResultEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface UserRoleDao {
    //根据用户id查询所有的角色id
    List<Integer> selectRoleId(@Param("userId") Integer userId);
    //根据用户id删除所有角色id
    Boolean deleteRoleId(@Param("userId") Integer userId);
    //新增用户角色
    Boolean insertRoleId(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
}
