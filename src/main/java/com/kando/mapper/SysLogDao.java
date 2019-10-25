package com.kando.mapper;

import com.kando.entity.SysLogEntity;

import java.sql.SQLException;

/**
 * 系统日志Dao
 */
public interface SysLogDao {
    boolean insert(SysLogEntity entity)throws SQLException;

}
