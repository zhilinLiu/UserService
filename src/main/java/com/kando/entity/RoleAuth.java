package com.kando.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class RoleAuth implements Serializable {
    private Integer id;
    private Integer roleId;
    private Integer authId;
}
