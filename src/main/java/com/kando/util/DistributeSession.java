package com.kando.util;

import com.alibaba.fastjson.JSON;
import com.kando.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration("dsSession")
public class DistributeSession {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public void set(String key,Object value){
        String sessionId = request.getSession().getId();
        String v1 = JSON.toJSONString(value);
        redisTemplate.opsForHash().put(sessionId,key,v1);
    }

    public String get(String key){
        String id = request.getSession().getId();
        Object o = redisTemplate.opsForHash().get(id, key);
        if(o==null){
            return null;
        }
        String str = o.toString();
        redisTemplate.expire(id,30, TimeUnit.MINUTES);
        return str ;
    }

    /**
     *   先set 才能获取user
     * @return
     */
    public User getUser(){
        String id = request.getSession().getId();
        String str = redisTemplate.opsForHash().get(id, "user").toString();
        if(str==null){
            return null;
        }
        User user = JSON.parseObject(str,User.class);
        return user;
    }
}
