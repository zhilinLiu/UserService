package com.kando.service.impl;

import com.kando.dao.RoleAuthDao;
import com.kando.entity.Role;
import com.kando.service.RoleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class RoleAuthServiceImpl implements RoleAuthService {
    @Autowired
    private RoleAuthDao dao;

    @Override
    public boolean insertRoleAuth(Integer roleId, Integer authId) {
        return dao.insertRoleAuth(roleId,authId);
    }
}
