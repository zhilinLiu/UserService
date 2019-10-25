package com.kando.common.exception;

import lombok.Getter;

@Getter
  public enum ResultEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    USER_NOT_EXIST(10, "用户不存在"),
    USER_STOCK_ERROR(11, "用户信息不正确"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
