package com.kando.entity.busEntity;

import lombok.Data;

import java.io.Serializable;

/**
 * 方案实体
 * 表名 pr_fangan
 */
@Data

public class PrFanganEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */

    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 输入转动
     */
    private String inDanxiang;
    /**
     * 输入往复
     */
    private String inWangfu;
    /**
     * 输入导向
     */
    private String inDaoxiang;
    /**
     * 输出转向
     */
    private String outZhuangdong;
    /**
     * 输出移动
     */
    private String outYidong;
    /**
     * 输出导向
     */
    private String outDaoxiang;
    /**
     * 组织机构id
     */
    private String orgId;
    /**
     * 创建人id
     */
    private String createUserId;
    /**
     * 逻辑删除字段
     */
    private Integer deleted;
}
