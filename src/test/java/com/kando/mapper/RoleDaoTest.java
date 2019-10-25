package com.kando.mapper;

import com.kando.entity.Organization;
import com.kando.entity.Role;
import com.kando.service.impl.RoleServiceImpl;
import com.kando.util.AuthCode;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RoleDaoTest {
    @Autowired
    private RoleDao authorityDao;
    @Autowired
    RoleServiceImpl service;

    @Test
    public void test(){
        Role role = authorityDao.selectRole(1);
        System.out.println(role);
    }


}