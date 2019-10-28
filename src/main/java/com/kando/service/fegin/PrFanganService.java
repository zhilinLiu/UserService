package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.ao.PrVo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrFanganEntity;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@FeignClient(value = "bus-service",path = "pr/fangan",fallbackFactory = PrFanganService.PrFangganFeginFallback.class)
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
    @Slf4j
    public class PrFangganFeginFallback implements FallbackFactory<PrFanganService> {

        @Override
        public PrFanganService create(Throwable throwable) {
            throwable.printStackTrace();
            return new PrFanganService() {
                @Override
                public JSONResponse queryAll(Map<String, Object> params) {
                    log.error("调用服务#{}的#{}方法失败","PrFanggan","queryAll");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse list(Map<String, Object> params) {
                    log.error("调用服务#{}的#{}方法失败","PrFanggan","list");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse select(PrVo vo) {
                    log.error("调用服务#{}的#{}方法失败","PrFanggan","select");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse info(String id) {
                    log.error("调用服务#{}的#{}方法失败","PrFanggan","info");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse save(PrFanganEntity prFangan) {
                    log.error("调用服务#{}的#{}方法失败","PrFanggan","save");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse update(PrFanganEntity prFangan) {
                    log.error("调用服务#{}的#{}方法失败","PrFanggan","update");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }

                @Override
                public JSONResponse delete(IdAo ao) {
                    log.error("调用服务#{}的#{}方法失败","PrFanggan","delete");
                    return new JSONResponse(false,"1",throwable.getMessage(),null);
                }
            };
        }
    }
}
