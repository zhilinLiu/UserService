package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.ao.PrProdVo;
import com.kando.dto.JSONResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@FeignClient(value = "bus-service", path = "pr/production",fallbackFactory = PrProductionService.PrProductFallback.class)
public interface PrProductionService {
    /**
     * 查看所有列表
     *
     * @param params 查询参数
     * @return JSONResponse
     */
    @RequestMapping("/all")
    public JSONResponse queryAll(@RequestParam Map<String, Object> params);

    /**
     * 分页查询机构部件
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
     * 新增机构部件
     *
     * @param vo vo
     * @return JSONResponse
     */
    @RequestMapping("/save")
    public JSONResponse save(@RequestBody PrProdVo vo);

    /**
     * 修改机构部件
     *
     * @param vo vo
     * @return JSONResponse
     */
    @RequestMapping("/update")
    public JSONResponse update(@RequestBody PrProdVo vo);

    /**
     * 根据主键删除机构部件
     *
     * @param ao ao
     * @return RestResponse
     */
    @RequestMapping("/delete")
    public JSONResponse delete(@RequestBody IdAo ao);

    @Component
    @Slf4j
    public class PrProductFallback implements FallbackFactory<PrProductionService> {
        @Override
        public PrProductionService create(Throwable throwable) {
            throwable.printStackTrace();
            return new PrProductionService() {
                @Override
                public JSONResponse queryAll(Map<String, Object> params) {
                    log.error("调用服务#{}的#{}方法失败", "PrProduct", "queryAll");
                    return new JSONResponse(false, "1", throwable.getMessage(), null);
                }

                @Override
                public JSONResponse list(Map<String, Object> params) {
                    log.error("调用服务#{}的#{}方法失败", "PrProduct", "list");
                    return new JSONResponse(false, "1", throwable.getMessage(), null);
                }

                @Override
                public JSONResponse info(String id) {
                    log.error("调用服务#{}的#{}方法失败", "PrProduct", "info");
                    return new JSONResponse(false, "1", throwable.getMessage(), null);
                }

                @Override
                public JSONResponse save(PrProdVo vo) {
                    log.error("调用服务#{}的#{}方法失败", "PrProduct", "save");
                    return new JSONResponse(false, "1", throwable.getMessage(), null);
                }

                @Override
                public JSONResponse update(PrProdVo vo) {
                    log.error("调用服务#{}的#{}方法失败", "PrProduct", "update");
                    return new JSONResponse(false, "1", throwable.getMessage(), null);
                }

                @Override
                public JSONResponse delete(IdAo ao) {
                    log.error("调用服务#{}的#{}方法失败", "PrProduct", "delete");
                    return new JSONResponse(false, "1", throwable.getMessage(), null);
                }

            };
        }
    }
    }
