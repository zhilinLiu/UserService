package com.kando.common.exception;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    //通用异常,
    PARAM_ERROR(1, "参数不正确"),
    UNKNOWN_ERROR(2,"未知异常，请联系系统管理员"),
    //用户异常
    USER_NOT_EXIST_ERROR(101, "用户不存在"),
    USER_STOCK_ERROR(102, "手机或密码不正确"),
    Code_long_ERROR(103,"验证码已超时"),
    PHONE_STOCK_ERROR(104, "手机或验证码不正确"),
    PHONE_IS_EXIST_ERROR(105,"用户已存在"),
    DELETE_USER_ERROR(106,"删除用户失败"),
    //邮箱异常
    EMAIL_STOCK_ERROR(201, "邮箱或验证码不正确"),
    EMAIL_NULL_ERROR(202,"邮箱或验证码不能为空"),
    EMAIL_ERROR(203,"绑定邮箱失败"),
    EMAIL_IS_EXIST_ERROR(204,"邮箱已经被绑定"),
    EMAIL_SEND_FAILED(205,"邮箱发送失败"),
   //单位异常
    UNIT_ID_IS_NOT_EXIST_ERROR(301,"单位id不存在"),
    UNIT_IS_NOT_EXIST_ERROR(302,"单位不存在"),
    DELETE_UNIT_ERROR(303,"删除单位失败"),
    INSERT_UNIT_ERROR(304,"新增单位失败");
    private Integer code;
    private String message;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}