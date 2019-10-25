package com.kando.service;

import com.alibaba.fastjson.JSON;
import com.kando.entity.User;
import com.kando.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StringRedisTemplate redis;
    @Autowired
    private HttpServletRequest request;
    @Test
    public void test1(){
        System.out.println("aaa");
    }

    @Test
    public void test2(){
        User user = new User();
        user.setPhone("12121");
        User user1;

        String user2 = redis.opsForHash().get("133", "user").toString();
        User user3 = JSON.parseObject(user2, User.class);
        System.out.println(user3);


    }
}