package com.kando.entity;

import lombok.Data;

@Data
public class UserRole {
    private Integer id;//中间表ID

    private Integer userId;//用户ID

    private Integer roleId;//角色ID
}
