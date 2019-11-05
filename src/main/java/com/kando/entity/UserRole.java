package com.kando.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {
    private Integer id;//中间表ID

    private Integer userId;//用户ID

    private Integer roleId;//角色ID
}
