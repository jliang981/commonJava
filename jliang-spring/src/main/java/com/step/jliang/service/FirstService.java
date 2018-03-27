package com.step.jliang.service;

import com.step.jliang.module.ChaiDog;

/**
 * @author jliang
 */
public interface FirstService {

    ChaiDog getOne(String name);

    ChaiDog save(ChaiDog dog);

    boolean delete(String name);
}
