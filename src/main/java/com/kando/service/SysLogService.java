package com.kando.service;

import com.kando.dto.QueryResult;
import com.kando.entity.SysLogEntity;
import com.kando.vo.PageVo;

/**
 * 系统日志
 */
public interface SysLogService {
    /**
     * 保存日志
     * @param entity 日志实体
     * @return IPage
     */
    boolean save(SysLogEntity entity) throws Exception;
    /**
     * 查看日志
     * @param vo 查询条件
     * @return List<SysLogEntity>
     */
    QueryResult<SysLogEntity> selectAll(PageVo vo);
}
