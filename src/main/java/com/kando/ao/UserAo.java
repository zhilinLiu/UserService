package com.kando.ao;

import com.kando.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class UserAo {
    private Integer id;//主键id

    private String userName;//用户名

    private String password;//密码

    private Integer sex;//性别

    private String email;//邮箱

    private String phone;//手机号

    private Integer status;//账号状态

    private List<Role> roles;//权限

    private String seccode;//验证码

    private ArrayList<Integer> roleId;//角色id
}
