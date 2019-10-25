package com.kando.controller.busController;

import com.kando.ao.PrProdVo;
import com.kando.dto.JSONResponse;
import com.kando.service.fegin.PrProductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("pr/production")
@Slf4j
public class PrProductionController {
    @Autowired
    private PrProductionService service;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params){
        try {
            log.info("正在执行---------   queryAll............");
            return service.queryAll(params);
        } catch (Exception e) {
            log.error("queryAll  -----执行失败..........");
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 分页查询机构部件
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @GetMapping("/page")
    public JSONResponse list(@RequestParam Map<String, Object> params){
        try {
            log.info("正在执行---------   list............");
            return service.list(params);
        } catch (Exception e) {
            log.error("list  -----执行失败..........");
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return JSONResponse
     */
    @RequestMapping("/info/{id}")
    public JSONResponse info(@PathVariable("id") String id){
        try {
            log.info("正在执行---------   info............");
            return service.info(id);
        } catch (Exception e) {
            log.error("info  -----执行失败..........");
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 新增机构部件
     *
     * @param vo vo
     * @return JSONResponse
     */
    @RequestMapping("/save")
    public JSONResponse save(@RequestBody PrProdVo vo){
        try {
            log.info("正在执行---------   save............");
            return service.save(vo);
        } catch (Exception e) {
            log.error("save  -----执行失败..........");
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 修改机构部件
     *
     * @param vo vo
     * @return JSONResponse
     */
    @RequestMapping("/update")
    public JSONResponse update(@RequestBody PrProdVo vo){
        try {
            log.info("正在执行---------   update............");
            return service.update(vo);
        } catch (Exception e) {
            log.error("update  -----执行失败..........");
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }
}
