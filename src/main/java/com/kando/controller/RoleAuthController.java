package com.kando.controller;

import com.kando.dto.Result;
import com.kando.service.impl.RoleAuthServiceImpl;
import com.kando.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/RoleAuth")
@Slf4j
public class RoleAuthController {
    @Autowired
    private RoleAuthServiceImpl service;

    //添加角色权限
    @RequestMapping("/insert")
    public Result insert(Integer roleId,Integer authId){
        log.info("执行 insert方法，传入roleId，authID分别为#{}，#{}",roleId,authId);
        Result<Object> result ;
        if(service.insertRoleAuth(roleId,authId)){
            log.info("插入权限成功");
            result = ResultUtil.success("插入成功");
        }else {
            result = ResultUtil.fail("插入失败");
        }
        return result;
    }
}
