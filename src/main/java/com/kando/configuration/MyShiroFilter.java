package com.kando.configuration;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.kando.common.utils.HttpContextUtils;
import com.kando.entity.Result;
import com.kando.entity.Role;
import com.kando.util.TokenNullException;
import com.kando.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * lzl
 */
@Slf4j
public class MyShiroFilter extends AuthenticatingFilter {
    private StringRedisTemplate stringRedisTemplate;

    MyShiroFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        try {
//            从request里得到tokenId
            String tokenId = getRequestToken(httpServletRequest);
            String UseramePassword = (String) stringRedisTemplate.opsForValue().get("token:"+tokenId);
            stringRedisTemplate.expire("token:"+tokenId,600, TimeUnit.SECONDS);
            Long expire = stringRedisTemplate.getExpire("token:" + tokenId,TimeUnit.SECONDS);
            log.info("接受到的token超时时间为:"+expire);
            String[] split = UseramePassword.split(",");
            String userName = split[0];
            String passWord = split[1];
            UsernamePasswordToken myshiroToken = new UsernamePasswordToken(userName, passWord);
            return myshiroToken;
        }catch (TokenNullException e){
            //没从请求头获取到token
            return new UsernamePasswordToken("NullToken", "null1");
        }catch (NullPointerException e){
            //请求头获取的token从redis没拿到
            return new UsernamePasswordToken("NullPointer", "null1");
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        boolean flag = executeLogin(request, response);
        log.info("is doing onAccessDenied,the result is " + flag);
        return flag;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
//        log.info("is doing onLoginFailure,that will return false ");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        String message = e.getMessage();
        if(message.contains("NullToken")){
            Result<String> result = new Result<>();
            result.setCode(401);
            result.setMessage("没有登录禁止访问");
            result.setSuccess(false);
            try {
                httpResponse.getWriter().print(JSON.toJSONString(result));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else if(message.contains("NullPointer")){
            Result<String> result = new Result<>();
            result.setCode(402);
            result.setMessage("token过期，请重新登录");
            result.setSuccess(false);
            try {
                httpResponse.getWriter().print(JSON.toJSONString(result));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else {
            Result<String> result = new Result<>();
            result.setCode(401);
            result.setMessage("没有登录禁止访问");
            result.setSuccess(false);
            try {
                httpResponse.getWriter().print(JSON.toJSONString(result));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    private String getRequestToken(HttpServletRequest httpRequest) throws TokenNullException {
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }

        //如果都没有，则抛出异常
        if(token==null||token.equals("")){
            throw new TokenNullException("token为空");
        }

        return token;
    }
}
