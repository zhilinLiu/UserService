package com.kando.ao;

import com.kando.entity.Authority;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Data
public class RoleAo implements Serializable {
    private Integer id;

    private String name;

    private String description;

    private Integer status;

    private String createUserId;

    private List<Integer> authId;

}
