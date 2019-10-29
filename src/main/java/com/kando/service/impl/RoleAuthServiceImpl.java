package com.kando.service.impl;

import com.kando.dao.RoleAuthDao;
import com.kando.entity.Role;
import com.kando.service.RoleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class RoleAuthServiceImpl implements RoleAuthService {
    @Autowired
    private RoleAuthDao dao;

    @Override
    @Transactional
    public boolean insertRoleAuth(Integer roleId, Integer authId) {
        return dao.insertRoleAuth(roleId,authId);
    }

    @Override
    public boolean deleteRoleAuth(Integer roleId, Integer authId) {
        return dao.deleteRoleAuth(roleId, authId);
    }

    @Override
    @Transactional
    public boolean updateRoleAuth(Integer roleId, Integer authId,Integer newAuthId) {
        return dao.updateRoleAuth(roleId, authId, newAuthId);
    }

    @Override
    @Transactional
    public boolean deleteAllAuth(Integer roleId) {
        return dao.deleteAllAuth(roleId);
    }
}
