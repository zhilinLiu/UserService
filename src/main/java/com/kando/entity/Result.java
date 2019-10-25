package com.kando.entity;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private boolean isSuccess;
    private String message;
    private T data;

}
