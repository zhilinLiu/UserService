package com.kando.common.exception;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    USER_NOT_EXIST_ERROR(10, "用户不存在"),
    USER_STOCK_ERROR(11, "手机或密码不正确"),
    EMAIL_STOCK_ERROR(12, "邮箱或验证码不正确"),
    EMAIL_NULL_ERROR(13,"邮箱或验证码不能为空"),
    Code_long_ERROR(14,"验证码已超时"),
    PHONE_STOCK_ERROR(15, "手机或验证码不正确"),
    PHONE_IS_EXIST_ERROR(16,"用户已存在"),
    UNIT_ID_IS_NOT_EXIST_ERROR(17,"单位id不存在"),
    UNIT_IS_NOT_EXIST_ERROR(18,"单位不存在");
    private Integer code;
    private String message;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}