package com.kando.controller;

import com.kando.dto.QueryResult;
import com.kando.dto.Result;
import com.kando.entity.SysLogEntity;
import com.kando.service.SysLogService;
import com.kando.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys/log")
public class SysLogController {
    @Autowired
    SysLogService sysLogService;
    @GetMapping("/page")
    public Result page(PageVo vo){

//        QueryResult<SysLogEntity> list = sysLogService.selectAll(vo);
        Result<QueryResult<SysLogEntity>> result = new Result<>();
//        result.setData(list);
        return result;
    }
}
