
package com.kando.entity.busEntity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典分组实体
 * 表名 pr_dict_group
 *
 */
@Data

public class PrDictGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    private String id;
    /**
     * 分组编码
     */
    @NotBlank(message = "分组编码不能为空")
    private String code;
    /**
     * 分组名称
     */
    @NotBlank(message = "分组名称不能为空")
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;
}
