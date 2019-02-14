package com.step.jliang.rpc.service.impl;

import com.step.jliang.rpc.service.HelloService;

/**
 * @author jliang
 */
public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "Hello " + name;
    }
}
