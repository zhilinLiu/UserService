package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.ao.PrVo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrFanganEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@FeignClient(value = "bus-service",path = "pr/fangan",fallback = PrFanganService.PrFangganFeginFallback.class)
public interface PrFanganService {
    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params);

    /**
     * 分页查询方案
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @GetMapping("/page")
    public JSONResponse list(@RequestParam Map<String, Object> params);


    /**
     * 根据条件查询解决方案的机构
     *
     * @param vo 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/select")
    public JSONResponse select(@RequestBody PrVo vo);

    /**
     * 根据主键查询详情
     *
     * @param id 主键
     * @return JSONResponse
     */
    @RequestMapping("/info/{id}")
    public JSONResponse info(@PathVariable("id") String id);

    /**
     * 新增方案
     *
     * @param prFangan prFangan
     * @return JSONResponse
     */
    @RequestMapping("/save")
    public JSONResponse save(@RequestBody PrFanganEntity prFangan);


    /**
     * 修改方案
     *
     * @param prFangan prFangan
     * @return JSONResponse
     */
    @RequestMapping("/update")
    public JSONResponse update(@RequestBody PrFanganEntity prFangan);


    /**
     * 根据主键(批量)删除方案
     *
     * @param ao ao
     * @return JSONResponse
     */
    @RequestMapping("/delete")
    public JSONResponse delete(@RequestBody IdAo ao);

    @Component
    public class PrFangganFeginFallback implements PrFanganService {

        @Override
        public JSONResponse queryAll(Map<String, Object> params) {
            return new JSONResponse(false,"1","远程调用PrFangganService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse list(Map<String, Object> params) {
            return new JSONResponse(false,"1","远程调用PrFangganService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse select(PrVo vo) {
            return new JSONResponse(false,"1","远程调用PrFangganService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse info(String id) {
            return new JSONResponse(false,"1","远程调用PrFangganService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse save(PrFanganEntity prFangan) {
            return new JSONResponse(false,"1","远程调用PrFangganService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse update(PrFanganEntity prFangan) {
            return new JSONResponse(false,"1","远程调用PrFangganService失败了，服务未开启或者方法出问题",null);
        }

        @Override
        public JSONResponse delete(IdAo ao) {
            return new JSONResponse(false,"1","远程调用PrFangganService失败了，服务未开启或者方法出问题",null);
        }
    }
}
