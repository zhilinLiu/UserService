package com.kando.dao;
import com.kando.dao.AuthorityDao;
import com.kando.entity.*;
import com.kando.service.RoleService;
import com.kando.service.impl.RoleServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityDaoTest {
    @Autowired
    AuthorityDao authorityDao;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    RoleAuthDao roleAuthDao;
    @Test
    public void  test(){
        Role role = roleService.selectRole(1);
        System.out.println(role);
    }
}