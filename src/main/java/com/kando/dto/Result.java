package com.kando.dto;

import lombok.Data;

@Data
public class Result<T> {
    public Object set;
    private int code;
    private boolean isSuccess;
    private String message;
    private T data;
    private String token;

}
