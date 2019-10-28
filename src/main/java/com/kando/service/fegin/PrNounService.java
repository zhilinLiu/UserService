package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrNounEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@FeignClient(value = "bus-service", path = "pr/noun",fallback = PrNounService.PrNounFallback.class)
public interface PrNounService {
    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params);

    /**
     * 分页查询名词解释
     *
     * @param params 查询参数
     * @return RestResponse
     */
    @GetMapping("/page")
    public JSONResponse list(@RequestParam Map<String, Object> params);

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return RestResponse
     */
    @GetMapping("/info/{id}")
    public JSONResponse info(@PathVariable("id") String id);

    /**
     * 新增名词解释
     *
     * @param prNoun prNoun
     * @return RestResponse
     */
    @PostMapping("/save")
    public JSONResponse save(@RequestBody PrNounEntity prNoun);

    /**
     * 修改名词解释
     *
     * @param prNoun prNoun
     * @return RestResponse
     */
    @PostMapping("/update")
    public JSONResponse update(@RequestBody PrNounEntity prNoun);

    /**
     * 根据主键（批量）删除名词解释
     * @param ao ao
     * @return RestResponse
     */
    @PostMapping("/delete")
    @ResponseBody
    public JSONResponse delete(@RequestBody IdAo ao);

    @Component
    @Slf4j
    public class PrNounFallback implements PrNounService {

        @Override
        public JSONResponse queryAll(Map<String, Object> params) {
            log.error("调用服务#{}的#{}方法失败","PrNoun","queryAll");
            return new JSONResponse(false,"1","远程调用PrNounService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse list(Map<String, Object> params) {
            log.error("调用服务#{}的#{}方法失败","PrNoun","list");
            return new JSONResponse(false,"1","远程调用PrNounService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse info(String id) {
            log.error("调用服务#{}的#{}方法失败","PrNoun","info");
            return new JSONResponse(false,"1","远程调用PrNounService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse save(PrNounEntity prNoun) {
            log.error("调用服务#{}的#{}方法失败","PrNoun","save");
            return new JSONResponse(false,"1","远程调用PrNounService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse update(PrNounEntity prNoun) {
            log.error("调用服务#{}的#{}方法失败","PrNoun","update");
            return new JSONResponse(false,"1","远程调用PrNounService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse delete(IdAo ao) {
            log.error("调用服务#{}的#{}方法失败","PrNoun","delete");
            return new JSONResponse(false,"1","远程调用PrNounService失败了，服务未开启或者方法出问题",null);
        }
    }
}
