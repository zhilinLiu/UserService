package com.kando.configuration;

import com.kando.common.utils.ResultUtils;
import com.kando.common.exception.MeioException;
import com.kando.dto.Result;
import com.kando.util.UserNotExsistException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@ControllerAdvice
public class MeioExceptionHandler {
    @ExceptionHandler(value = MeioException.class)
    @ResponseBody
    public Result handlerSellerException(MeioException e) {
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Result authorizationException(){
        Result result = new Result();
        result.setCode(200);
        result.setMessage("用户不存在或者密码错误");
        return result;
    }

    @ExceptionHandler(value = {NoSuchAlgorithmException.class, UnsupportedEncodingException.class})
    @ResponseBody
    public Result MD5Exception(){
        Result result = new Result();
        result.setCode(500);
        result.setSuccess(false);
        result.setMessage("密码加密失败，不支持的加密类型");
        return result;
    }

    @ExceptionHandler(value = UserNotExsistException.class)
    @ResponseBody
    public Result userNotExisist(Exception e){
        Result result = new Result();
        result.setCode(500);
        result.setSuccess(false);
        result.setMessage(e.getMessage());
        return result;
    }

}
