
package com.kando.entity.busEntity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 数据字典实体
 * 表名 pr_dict
 *
 */
@Data
public class PrDictEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 所属分组ID
     */
    @NotBlank(message = "所属分组不能为空")
    private String groupId;
    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    private String name;
    /**
     * 字典值
     */
    @NotBlank(message = "字典值不能为空")
    private String value;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;


    private String code;
}
