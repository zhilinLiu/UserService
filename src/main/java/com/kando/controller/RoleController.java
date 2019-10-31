package com.kando.controller;

import com.kando.ao.RoleAo;
import com.kando.dto.QueryResult;
import com.kando.entity.Result;
import com.kando.entity.Role;
import com.kando.service.impl.RoleServiceImpl;
import com.kando.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限请求
 */
@RestController
@Slf4j
public class RoleController {
    @Autowired
    private RoleServiceImpl service;

    



    /**
     * 添加角色
     */
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public Result inserRole(@RequestBody RoleAo role1){
        log.info("调用增加角色，传入的参数为:"+role1);
        Result<Object> result = new Result<>();
        if (service.inserRole(role1)){
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("新增角色成功");
        }else {
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("新增角色失败");
        }
        return result;
    }

    @RequestMapping("/cancelRole")
    public Result deleteRole(Integer id){
        log.info("执行删除角色方法，接受到的id为："+id);
        Result<Object> result = new Result<>();
        if(service.deleteRole(id)){
            log.info("删除角色成功");
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("删除角色成功");
        }else {
            log.error("删除角色失败,没有这个角色或者角色已经被删除");
            result.setCode(0);
            result.setSuccess(false);
            result.setMessage("删除角色失败,没有这个角色或者角色已经被删除");
        }
        return result;
    }
    //更新角色，需要传入id,name,description,status
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    public Result updateRole(@RequestBody RoleAo role1){
        log.info("执行更新角色的方法，接受到的参数为"+role1);
        Result<Object> result = new Result<>();
        if(service.updateRole(role1)){
            log.info("更新角色成功");
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("更新角色成功");
        }else {
            log.error("更新角色失败");
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("更新角色失败");
        }
        return result;
    }

    /**
     * 根据id查询角色信息,get方法
     * @param id
     * @return Result
     */
    @RequestMapping("/queryOneRole")
    public Result<Role> selectRole(Integer id){
        log.info("正在执行根据id查询角色信息,接受到的参数为："+id);
        Result<Role> result = new Result<>();
        Role role = service.selectRole(id);
        if(role!=null){
            log.info("查询成功");
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("根据id查询角色成功");
            result.setData(role);
        }else {
            log.error("查询失败");
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("查询失败，没有返回数据");
        }
        return result;
    }

    @RequestMapping("/queryAll")
    public Result selectAll(PageVo vo){
        log.info("正在执行  queryAll,接受到的参数为："+vo);
        Result<QueryResult> result = new Result<>();
        QueryResult queryResult = service.queryAllRoles(vo);
        if(queryResult!=null){
            log.info("查询成功");
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("根据id查询角色成功");
            result.setData(queryResult);
        }else {
            log.error("查询失败");
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("查询失败，没有返回数据");
        }
        return result;
    }


    @RequestMapping("/roleAll")
    public Result all(){
        log.info("正在执行  roleAll,接受到的参数为：");
        Result<List<Role>> result = new Result<>();
        List<Role> all = service.all();
        if(all!=null){
            log.info("查询成功");
            result.setCode(0);
            result.setSuccess(true);
            result.setMessage("查询角色成功");
            result.setData(all);
        }else {
            log.error("查询失败");
            result.setCode(1);
            result.setSuccess(false);
            result.setMessage("查询失败，没有返回数据");
        }
        return result;
    }


}
