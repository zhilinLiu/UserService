package com.kando.configuration;

import com.kando.util.RedisUtil;
import com.kando.util.SerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *lzl
 */

@Slf4j
public class RedisCache implements Cache {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private String id;
    private String cacheName;

    public RedisCache(String id) {
        this.id = id;
        this.cacheName = UUID.randomUUID().toString();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void putObject(Object key, Object value) {
        Long mybatisCache = null;
        try {
            mybatisCache = RedisUtil.getJedis().hset(("mybatisCache:"+cacheName).getBytes("utf-8"),turnKey(key),SerializeUtil.serialize(value));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.debug("putObject is putting #{}",mybatisCache);
    }

    @Override
    public Object getObject(Object key) {
        Object result =null;

        try {
            byte[] bytes = RedisUtil.getJedis().hget(("mybatisCache:"+cacheName).getBytes("utf-8"), turnKey(key));
            result = SerializeUtil.deserialize(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.debug("is doing getObject");
        return result;
    }

    @Override
    public Object removeObject(Object key) {
        Long mybatisCache = null;
        try {
            mybatisCache = RedisUtil.getJedis().hdel(("mybatisCache:"+cacheName).getBytes("utf-8"),turnKey(key));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mybatisCache;
    }

    @Override
    public void clear() {
        try {
            RedisUtil.getJedis().del(("mybatisCache:"+cacheName).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.debug("delete mybatisCache.");
    }

    @Override
    public int getSize() {
        int mybatisCache = 0;
        try {
            mybatisCache = RedisUtil.getJedis().hgetAll(("mybatisCache:"+cacheName).getBytes("utf-8")).size();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.debug("is doing getSize ,the values is "+ mybatisCache);
        return mybatisCache;
    }

    public byte[] turnKey(Object object) throws UnsupportedEncodingException {
        if(object==null){
            return null;
        }
        String str = object.toString();
        String[] split = str.split(":");
        String newKey = split[2]+":"+split[5]+":"+split[6];
        return newKey.getBytes("utf-8");
    }
}
