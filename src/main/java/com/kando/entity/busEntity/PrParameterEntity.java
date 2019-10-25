package com.kando.entity.busEntity;

import lombok.Data;

import java.io.Serializable;

/**
 * 部件参数实体
 * 表名 pr_parameter
 */
@Data
public class PrParameterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 作品id
     */
    private String pdId;
    /**
     * 参数名
     */
    private String keyName;
    /**
     * 参数值
     */
    private String value;


}
