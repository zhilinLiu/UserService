package com.kando.entity.busEntity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构部件实体
 * 表名 pr_production
 */
@Data
public class PrProductionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 名字
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 简图
     */
    private String picture;
    /**
     * 状态：0未审核，1审核中，2审核通过，3审核为通过
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 审核失败原因
     */
    private String reason;
    /**
     * 创建人id
     */
    private String createUserId;
    /**
     * 组织机构id
     */
    private String orgId;
    /**
     * 逻辑删除字1未删除0已删除
     */
    private Integer deleted;
}
