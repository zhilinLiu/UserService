package com.kando.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

/**
 * @author 孙雨佳
 * @ClassName: User
 * @Description: TODO(用户表实体类)
 * @date 2019年10月24日
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;//主键id

    private String userName;//用户名

    private String password;//密码

    private Integer sex;//性别

    private String email;//邮箱

    private String phone;//手机号

    private Date createTime;//创建时间

    private Integer status;//账号状态

    private List<Role> roles;//角色

    private String seccode;//手机验证码

    private ArrayList<Integer> roleId;//角色id

    private String scode;//图片验证码

    private String uuid;//绑定图片验证的唯一标识

    private String newPassword;//新密码

}
