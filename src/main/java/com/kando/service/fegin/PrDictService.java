package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrDictEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@FeignClient(value = "bus-service",path = "pr/dict",fallback = PrDictService.PrDictFallback.class)
public interface PrDictService {
    @RequestMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params);

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @GetMapping("/page")
    public JSONResponse list(@RequestParam Map<String, Object> params);



    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return JSONResponse
     */
    @RequestMapping("/info/{id}")
    public JSONResponse info(@PathVariable("id") String id);


    /**
     * 保存
     * @return JSONResponse
     */
    @RequestMapping("/save")
    public JSONResponse save(@RequestBody PrDictEntity prDict);


    /**
     * 修改
     * @return JSONResponse
     */
    @RequestMapping("/update")
    public JSONResponse update(@RequestBody PrDictEntity prDict);


    /**
     * 删除
     * @return JSONResponse
     */
    @RequestMapping("/delete")
    public JSONResponse delete(@RequestBody IdAo ao);


    /**
     * 根据code查询数据字典
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/queryByCode")
    public JSONResponse queryByCode(@RequestParam Map<String, Object> params);




    @Component
    @Slf4j
    public class PrDictFallback implements PrDictService {

        @Override
        public JSONResponse queryAll(Map<String, Object> params) {
            log.error("调用服务#{}的#{}方法失败","PrDict","queryAll");
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse list(Map<String, Object> params) {
            log.error("调用服务#{}的#{}方法失败","PrDict","list");
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse info(String id) {
            log.error("调用服务#{}的#{}方法失败","PrDict","info");
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse save(PrDictEntity prDict) {
            log.error("调用服务#{}的#{}方法失败","PrDict","save");
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse update(PrDictEntity prDict) {
            log.error("调用服务#{}的#{}方法失败","PrDict","update");
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse delete(IdAo ao) {
            log.error("调用服务#{}的#{}方法失败","PrDict","delete");
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse queryByCode(Map<String, Object> params) {
            log.error("调用服务#{}的#{}方法失败","PrDict","queryByCode");
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }
    }
}
