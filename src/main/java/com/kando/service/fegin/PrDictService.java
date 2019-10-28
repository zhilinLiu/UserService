package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrDictEntity;
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
    public class PrDictFallback implements PrDictService {

        @Override
        public JSONResponse queryAll(Map<String, Object> params) {
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse list(Map<String, Object> params) {
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse info(String id) {
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse save(PrDictEntity prDict) {
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse update(PrDictEntity prDict) {
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse delete(IdAo ao) {
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse queryByCode(Map<String, Object> params) {
            return new JSONResponse(false,"1","远程调用PrDictService失败了，服务未开启或者方法出问题",null);
        }
    }
}
