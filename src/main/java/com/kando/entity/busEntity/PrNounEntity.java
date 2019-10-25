
package com.kando.entity.busEntity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 名词解释实体
 * 表名 pr_noun
 *
 */
@Data
public class PrNounEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */

    private String id;
    /**
     * 名词名称
     */
    private String name;
    /**
     * 名词解释
     */
    private String prExplain;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人id
     */
    private String createUserId;
    /**
     * 组织机构id
     */
    private String orgId;
    /**
     * 逻辑删除字段
     */

    private Integer deleted;
}
