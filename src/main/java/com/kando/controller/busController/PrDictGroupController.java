package com.kando.controller.busController;


import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrDictGroupEntity;
import com.kando.service.fegin.PrDictGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据字典分组Controller
 */
@RestController
@Slf4j
@RequestMapping("pr/dictgroup")
public class PrDictGroupController {
    @Autowired
    private PrDictGroupService prDictGroupService;


    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params) {
        try {
            log.info("is doing queryAll..........");
            return prDictGroupService.queryAll(params);

        } catch (Exception e) {
            log.error("queryAll failed............");
            return new JSONResponse(false,"1","远程调用失败",null);
        }

    }

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @GetMapping("/page")
    public JSONResponse list(@RequestParam Map<String, Object> params) {
        try {
            log.info("is doing list..........");
            return prDictGroupService.list(params);

        } catch (Exception e) {
            log.error("list failed............");
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
    public JSONResponse info(@PathVariable("id") String id) {
        try {
            log.info("is doing info..........");
        return prDictGroupService.info(id);
        } catch (Exception e) {
            log.error("info failed............");
             return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 保存数据字典分组
     *
     * @param prDictGroup prDictGroup
     * @return JSONResponse
     */
    @RequestMapping("/save")
    public JSONResponse save(@RequestBody PrDictGroupEntity prDictGroup) {
        try {
            log.info("is doing save..........");
            return prDictGroupService.save(prDictGroup);

        } catch (Exception e) {
            log.error("save failed............");
             return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 修改数据字典分组
     *
     * @param prDictGroup prDictGroup
     * @return JSONResponse
     */
    @RequestMapping("/update")
    public JSONResponse update(@RequestBody PrDictGroupEntity prDictGroup) {
        try {
            log.info("is doing update..........");
            return prDictGroupService.update(prDictGroup);

        } catch (Exception e) {
            log.error("update failed............");
             return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 删除数据字典分组
     *
     * @return JSONResponse
     */
    @RequestMapping("/delete")
    public JSONResponse delete(@RequestBody IdAo ao) {
        try {
            log.info("is doing delete..........");
            return prDictGroupService.delete(ao);

        } catch (Exception e) {
            log.error("delete failed............");
             return new JSONResponse(false,"1","远程调用失败",null);
        }
    }
}
