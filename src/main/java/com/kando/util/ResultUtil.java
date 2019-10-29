package com.kando.util;

import com.kando.dto.Result;

/**
 *
 */
public class ResultUtil {
    public static Result success(String message){
        Result<?> result = new Result<>();
        result.setCode(0);
        result.setSuccess(true);
        result.setMessage(message);
        return result;
    }

    public static Result fail(String message){
        Result<?> result = new Result<>();
        result.setCode(1);
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
