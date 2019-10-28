package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrDictGroupEntity;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@FeignClient(value = "bus-service",path = "pr/dictgroup",fallbackFactory = PrDictGroupService.PrDictGroupFegin.class)
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
    @Slf4j
    public class PrDictGroupFegin implements FallbackFactory<PrDictGroupService> {

        @Override
        public PrDictGroupService create(Throwable throwable) {
            throwable.printStackTrace();
            return new PrDictGroupService() {
                @Override
                public JSONResponse queryAll(Map<String, Object> params) {
                    log.error("调用服务#{}的#{}方法失败","PrDictGroup","queryAll");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse list(Map<String, Object> params) {
                    log.error("调用服务#{}的#{}方法失败","PrDictGroup","list");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse info(String id) {
                    log.error("调用服务#{}的#{}方法失败","PrDictGroup","info");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse save(PrDictGroupEntity prDictGroup) {
                    log.error("调用服务#{}的#{}方法失败","PrDictGroup","save");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse update(PrDictGroupEntity prDictGroup) {
                    log.error("调用服务#{}的#{}方法失败","PrDictGroup","update");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse delete(IdAo ao) {
                    log.error("调用服务#{}的#{}方法失败","PrDictGroup","delete");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }
            };
        }
    }
}
