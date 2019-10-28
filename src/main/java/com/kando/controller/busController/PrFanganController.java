package com.kando.controller.busController;

import com.kando.ao.IdAo;
import com.kando.ao.PrVo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrFanganEntity;
import com.kando.service.fegin.PrFanganService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@RestController
@Slf4j
@RequestMapping("pr/fangan")
public class PrFanganController {
    @Autowired
    PrFanganService service;
    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params){
        try {
            log.info("正在执行-------   queryAll........."+params);
            return service.queryAll(params);
        }catch (Exception e){
            log.error("queryAll   ----执行失败......"+params);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 分页查询方案
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @GetMapping("/page")
    public JSONResponse list(@RequestParam Map<String, Object> params){
        try {
            log.info("正在执行-------   list........."+params);
            return service.list(params);
        }catch (Exception e){
            log.error("list   ----执行失败......"+params);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }


    /**
     * 根据条件查询解决方案的机构
     *
     * @param vo 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/select")
    public JSONResponse select(@RequestBody PrVo vo){
        try {
            log.info("正在执行-------   select........."+vo);
            return select(vo);
        }catch (Exception e){
            log.error("select   ----执行失败......"+vo);
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
            log.info("正在执行-------   info........."+id);
            return service.info(id);
        }catch (Exception e){
            log.error("info   ----执行失败......"+id);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 新增方案
     *
     * @param prFangan prFangan
     * @return JSONResponse
     */
    @RequestMapping("/save")
    public JSONResponse save(@RequestBody PrFanganEntity prFangan){
        try {
            log.info("正在执行-------   save........."+prFangan);
            return service.save(prFangan);
        }catch (Exception e){
            log.error("save   ----执行失败......"+prFangan);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }


    /**
     * 修改方案
     *
     * @param prFangan prFangan
     * @return JSONResponse
     */
    @RequestMapping("/update")
    public JSONResponse update(@RequestBody PrFanganEntity prFangan){
        try {
            log.info("正在执行-------   update........."+prFangan);
            return service.update(prFangan);
        }catch (Exception e){
            log.error("update   ----执行失败......"+prFangan);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }


    /**
     * 根据主键(批量)删除方案
     *
     * @param ao ao
     * @return JSONResponse
     */
    @RequestMapping("/delete")
    public JSONResponse delete(@RequestBody IdAo ao){
        try {
            log.info("正在执行-------   delete........."+ao);
            return service.delete(ao);
        }catch (Exception e){
            log.error("delete   ----执行失败......"+ao);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }
}
