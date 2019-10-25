package com.kando.service;

import com.kando.entity.SysLogEntity;

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
}
