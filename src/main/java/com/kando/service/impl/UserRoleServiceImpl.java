package com.kando.service.impl;

import com.kando.common.exception.ResultEnum;
import com.kando.dao.UserRoleDao;
import com.kando.entity.Role;
import com.kando.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleServiceImpl roleService;

    //根据用户ID查询该用户下的所有角色以及角色下的权限
    @Override
    public List<Role> selectRoleId(Integer userId) {
        //从用户角色中间表查询出用户所对应的角色ID
        List<Integer> integers = userRoleDao.selectRoleId(userId);
//        再根据角色ID查询出角色
        ArrayList<Role> roles = new ArrayList<>();
        integers.forEach(id ->
                {
                    Role role = roleService.selectRole(id);
                    if (role != null) {
                        roles.add(role);
                    }
                }
        );
        return roles;
    }

    @Override
    @Transactional
    public Boolean deleteRoleId(Integer userId) {
        return userRoleDao.deleteRoleId(userId);
    }

    @Override
    @Transactional
    public Boolean insertRoleId(Integer userId,Integer roleId){
        return userRoleDao.insertRoleId(userId,roleId);
    }
}
