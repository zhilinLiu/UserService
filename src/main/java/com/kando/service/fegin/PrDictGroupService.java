package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrDictGroupEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@FeignClient(value = "bus-service",path = "pr/dict",fallback = PrDictGroupService.PrDictGroupFegin.class)
public interface PrDictGroupService {
    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return JSONResponse
     */
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
     * 保存数据字典分组
     *
     * @param prDictGroup prDictGroup
     * @return JSONResponse
     */
    @RequestMapping("/save")
    public JSONResponse save(@RequestBody PrDictGroupEntity prDictGroup);

    /**
     * 修改数据字典分组
     *
     * @param prDictGroup prDictGroup
     * @return JSONResponse
     */
    @RequestMapping("/update")
    public JSONResponse update(@RequestBody PrDictGroupEntity prDictGroup);

    /**
     * 删除数据字典分组
     * @return JSONResponse
     */
    @RequestMapping("/delete")
    public JSONResponse delete(@RequestBody IdAo ao);



    @Component
    public class PrDictGroupFegin implements PrDictGroupService {

        @Override
        public JSONResponse queryAll(Map<String, Object> params) {
            return new JSONResponse(false,"1","远程调用服务未开启",null);
    }

        @Override
        public JSONResponse list(Map<String, Object> params) {
            return new JSONResponse(false,"1","远程调用服务未开启",null);
        }

        @Override
        public JSONResponse info(String id) {
            return new JSONResponse(false,"1","远程调用服务未开启",null);
        }

        @Override
        public JSONResponse save(PrDictGroupEntity prDictGroup) {
            return new JSONResponse(false,"1","远程调用服务未开启",null);
        }

        @Override
        public JSONResponse update(PrDictGroupEntity prDictGroup) {
            return new JSONResponse(false,"1","远程调用服务未开启",null);
        }

        @Override
        public JSONResponse delete(IdAo ao) {
            return new JSONResponse(false,"1","远程调用服务未开启",null);
        }
    }
}
