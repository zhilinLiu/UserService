package com.kando.common.exception;

import lombok.Getter;

@Getter
public class MeioException extends RuntimeException {
    private Integer code;
    private String message;

    public MeioException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public MeioException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public MeioException(String message) {
        this.code = 400;
        this.message = message;
    }
 }