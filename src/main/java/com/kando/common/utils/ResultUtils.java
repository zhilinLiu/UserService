package com.kando.common.utils;

import com.kando.dto.Result;

public class ResultUtils {
    public static Result success(Object obj){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(0);
        result.setData(obj);
        result.setMessage("成功");
        return result;
    }
    public static Result success(){
        return success(null);
    }
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

}
