package com.kando.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface UserRoleDao {
    //根据用户id查询所有的角色id
    List<Integer> selectRoleId(@Param("userId") Integer userId);
}
