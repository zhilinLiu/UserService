package com.kando.controller.busController;

import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrNounEntity;
import com.kando.service.fegin.PrNounService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@RestController
@Slf4j
@RequestMapping("pr/noun")
public class PrNounController {
    @Autowired
    private PrNounService service;

    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params){
        try {
            log.info("正在执行-------   queryAll............"+params);
            return service.queryAll(params);
        } catch (Exception e) {
            log.error("queryAll  ----执行失败.........."+params);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 分页查询名词解释
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/page")
    public JSONResponse list(@RequestParam Map<String, Object> params){
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
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    public JSONResponse info(@PathVariable("id") String id){
        try {
            log.info("正在执行-------   info............"+id);
            return service.info(id);
        } catch (Exception e) {
            log.error("info  ----执行失败.........."+id);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 新增名词解释
     *
     * @param prNoun prNoun
     * @return RestResponse
     */
    @PostMapping("/save")
    public JSONResponse save(@RequestBody PrNounEntity prNoun){
        try {
            log.info("正在执行-------   save............"+prNoun);
            return service.save(prNoun);
        } catch (Exception e) {
            log.error("save  ----执行失败.........."+prNoun);
            e.printStackTrace();
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 修改名词解释
     *
     * @param prNoun prNoun
     * @return RestResponse
     */
    @PostMapping("/update")
    public JSONResponse update(@RequestBody PrNounEntity prNoun){
        try {
            log.info("正在执行-------   update............"+prNoun);
            return service.update(prNoun);
        } catch (Exception e) {
            log.error("update  ----执行失败.........."+prNoun);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }

    /**
     * 根据主键（批量）删除名词解释
     * @param ao ao
     * @return RestResponse
     */
    @PostMapping("/delete")
    @ResponseBody
    public JSONResponse delete(@RequestBody IdAo ao){
        try {
            log.info("正在执行-------   delete............"+ao);
            return service.delete(ao);
        } catch (Exception e) {
            log.error("delete  ----执行失败.........."+ao);
            return new JSONResponse(false,"1","远程调用失败",null);
        }
    }
}
