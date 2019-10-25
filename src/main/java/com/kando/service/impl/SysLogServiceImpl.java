package com.kando.service.impl;

import com.kando.common.IdWorker;
import com.kando.entity.SysLogEntity;
import com.kando.mapper.SysLogDao;
import com.kando.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统日志
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Resource
    SysLogDao dao;
    @Autowired
    IdWorker idWorker;
    /**
     * 保存日志
     *
     * @param entity 日志实体
     * @return IPage
     */
    @Override
    public boolean save(SysLogEntity entity) throws Exception {
        entity.setId(idWorker.nextId()+"");
        return dao.insert(entity);
    }
}
