package com.kando.controller;

import com.kando.dto.QueryResult;
import com.kando.entity.Authority;
import com.kando.entity.Result;
import com.kando.service.impl.AuthorityServiceImpl;
import com.kando.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限Controller
 */
@RestController
@Slf4j
@RequestMapping("auth")
public class AuthorityController {
    @Autowired
    AuthorityServiceImpl service;

    /**
     * 查询所有权限信息
     */
    @RequestMapping("/queryAll")
    public Result selectAll(PageVo vo){
        log.info("正在执行  selectAll,接受到的参数为："+vo);
        Result<QueryResult> result = new Result<>();
        QueryResult queryResult = service.selectAll(vo);
        if(queryResult!=null){
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("success");
            result.setData(queryResult);
        }else {
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("failed");
        }
        return result;
    }

    @RequestMapping("/queryOne")
    public Result queryOne(Integer id){
        log.info("正在执行  queryOne,接受到的参数为："+id);
        Result<Authority> result = new Result<>();
        Authority authority = service.selectOne(id);
        if(authority!=null){
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("success");
            result.setData(authority);
        }else {
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("failed");
        }
        return result;
    }

    @PostMapping("/insert")
    public Result queryOne(@RequestBody Authority authority){
        log.info("正在执行  queryOne,接受到的参数为："+authority);
        Result<Object> result = new Result<>();
        if(service.insertAuth(authority)){
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("sucess");
        }else {
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("failed");
        }
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody Authority authority){
        log.info("正在执行  update,接受到的参数为："+authority);
        Result<Object> result = new Result<>();
        if(service.updateAuth(authority)){
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("sucess");
        }else {
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("failed");
        }
        return result;
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        log.info("正在执行  delete,接受到的参数为："+id
        );
        Result<Object> result = new Result<>();
        if(service.deleteAuth(id)){
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("sucess");
        }else {
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("failed");
        }
        return result;
    }

}
