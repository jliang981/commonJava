package com.step.jliang.dao;

import com.step.jliang.module.ChaiDog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author jliang
 */
@Repository
public class RedisDao {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    public void save(String name, String value) {
        stringRedisTemplate.opsForValue().set(name, value);
    }

    public void save(ChaiDog dog) {
        redisTemplate.opsForValue().set(dog.getName(), dog);
    }

    public String getString(String name) {
        return stringRedisTemplate.opsForValue().get(name);
    }

    public ChaiDog getDog(ChaiDog dog) {
        return ((ChaiDog) redisTemplate.opsForValue().get(dog.getName()));
    }

}
