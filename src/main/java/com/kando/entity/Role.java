package com.kando.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
@Data
public class Role implements Serializable {
    private Integer id;

    private String name;

    private String description;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private List<Authority> authority;

}
