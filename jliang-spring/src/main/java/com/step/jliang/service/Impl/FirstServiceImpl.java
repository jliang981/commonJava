package com.step.jliang.service.Impl;

import com.step.jliang.module.ChaiDog;
import com.step.jliang.service.FirstService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author jliang
 */
@Service("firstService")
public class FirstServiceImpl implements FirstService {

    private int i = 1;

    @Override
    @Cacheable(value = "dog", key = "#name")
    public ChaiDog getOne(String name) {
        return new ChaiDog("jia2", i++);
    }

    @Override
    @CachePut(value = "dog", key = "#dog.name")
    public ChaiDog save(ChaiDog dog) {
        System.out.println("cache" + dog);
        return dog;
    }

    @Override
    @CacheEvict(value = "dog", key = "#name")
    public boolean delete(String name) {
        System.out.println("key:" + name + "isEvict");
        return true;
    }
}
