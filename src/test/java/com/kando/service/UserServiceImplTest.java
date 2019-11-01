package com.kando.service;



import com.kando.configuration.FTPUtils;
import com.kando.service.impl.UserServiceImpl;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public void test2() throws IOException {
        boolean flag = FTPUtils.uploadFile("666.jpg", "D:\\666.jpg");
        Assert.assertEquals(true, flag);

    }
}