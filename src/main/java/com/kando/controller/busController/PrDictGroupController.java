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
            log.info("is doing queryAll.........."+params);
            return prDictGroupService.queryAll(params);

        } catch (Exception e) {
            log.error("queryAll failed............"+params);
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
            log.info("is doing list.........."+params);
            return prDictGroupService.list(params);

        } catch (Exception e) {
            log.error("list failed............"+params);
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
            log.info("is doing info.........."+id);
        return prDictGroupService.info(id);
        } catch (Exception e) {
            log.error("info failed............"+id);
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
            log.info("is doing save.........."+prDictGroup);
            return prDictGroupService.save(prDictGroup);

        } catch (Exception e) {
            log.error("save failed............"+prDictGroup);
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
            log.info("is doing update.........."+prDictGroup);
            return prDictGroupService.update(prDictGroup);

        } catch (Exception e) {
            log.error("update failed............"+prDictGroup);
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
            log.info("is doing delete.........."+ao);
            return prDictGroupService.delete(ao);

        } catch (Exception e) {
            log.error("delete failed............"+ao);
             return new JSONResponse(false,"1","远程调用失败",null);
        }
    }
}
