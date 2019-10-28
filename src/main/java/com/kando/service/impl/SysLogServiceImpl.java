package com.kando.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kando.common.IdWorker;
import com.kando.dao.SysLogDao;
import com.kando.dto.QueryResult;
import com.kando.entity.SysLogEntity;
import com.kando.service.SysLogService;
import com.kando.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Override
    public QueryResult<SysLogEntity> selectAll(PageVo vo){
        PageHelper.startPage(vo.getPage(),vo.getLimit());
        List<SysLogEntity> list = dao.selectAll(vo.getKey());
        PageInfo<SysLogEntity> page = new PageInfo<>(list);
        // 3 组装结果
        QueryResult<SysLogEntity> result = new QueryResult<SysLogEntity>();
        result.setPageNo(vo.getPage());
        result.setRows(page.getList());
        result.setTotalRows(page.getTotal());
        result.setPageSize(vo.getLimit());
        return result;
    }
}
