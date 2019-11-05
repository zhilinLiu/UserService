package com.kando.dao;

import com.kando.entity.SysLogEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 系统日志Dao
 */
public interface SysLogDao {
    boolean insert(SysLogEntity entity)throws SQLException;
    List<SysLogEntity> selectAll(@Param("Key") String key);
}
