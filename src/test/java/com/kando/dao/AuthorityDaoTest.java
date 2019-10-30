package com.kando.dao;
import com.kando.ao.RoleAo;
import com.kando.controller.AuthorityController;
import com.kando.dao.AuthorityDao;
import com.kando.entity.*;
import com.kando.service.RoleService;
import com.kando.service.impl.RoleServiceImpl;
import com.kando.service.impl.UserRoleServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityDaoTest {
    @Autowired
    UserRoleServiceImpl service;
    @Test
    public void  test(){
        List<Role> roles = service.selectRoleId(1);
        roles.forEach(x-> System.out.println(x));
    }

}