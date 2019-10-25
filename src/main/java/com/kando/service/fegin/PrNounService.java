package com.kando.service.fegin;

import com.kando.ao.IdAo;
import com.kando.dto.JSONResponse;
import com.kando.entity.busEntity.PrNounEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 */
@FeignClient(value = "bus-service", path = "pr/noun")
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
}
