package com.kando.controller.busController;

import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrDictEntity;
import com.kando.service.fegin.PrDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("pr/dict")
@Slf4j
public class PrDictController {
    @Autowired
    private PrDictService service;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params) {
        try {
            log.info("正在执行-------   queryAll............");
            return service.queryAll(params);
        } catch (Exception e) {
            log.error("queryAll  ----执行失败..........");
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
            log.info("正在执行-------   list............"+params);
            return service.list(params);
        } catch (Exception e) {
            log.error("list  ----执行失败.........."+params);
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
            log.info("正在执行-------   info............"+id);
            return service.info(id);
        } catch (Exception e) {
            log.error("info  ----执行失败.........."+id);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 保存
     *
     * @return JSONResponse
     */
    @RequestMapping("/save")
    public JSONResponse save(@RequestBody PrDictEntity prDict) {
        try {
            log.info("正在执行-------   save............"+prDict);
            return service.save(prDict);
        } catch (Exception e) {
            log.error("save  ----执行失败.........."+prDict);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 修改
     *
     * @return JSONResponse
     */
    @RequestMapping("/update")
    public JSONResponse update(@RequestBody PrDictEntity prDict) {
        try {
            log.info("正在执行-------   update............"+prDict);
            return service.update(prDict);
        } catch (Exception e) {
            log.error("update  ----执行失败.........."+prDict);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 删除
     *
     * @return JSONResponse
     */
    @RequestMapping("/delete/{id}")
    public JSONResponse delete(@PathVariable("id") String id) {
        try {
            log.info("正在执行-------   delete............"+id);
            return service.delete(id);
        } catch (Exception e) {
            log.error("delete  ----执行失败.........."+id);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 根据code查询数据字典
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/queryByCode")
    public JSONResponse queryByCode(@RequestParam Map<String, Object> params) {
        try {
            log.info("正在执行-------   queryByCode............"+params);
            return service.queryByCode(params);
        } catch (Exception e) {
            log.error("queryByCode  ----执行失败.........."+params);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }
}
