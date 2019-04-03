package com.step.jliang.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author haoliang
 * @Date 2019-04-03
 **/
public class TestConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put(1, 1);
        map.put(3,3);
        for (Map.Entry<Object, Object> objectObjectEntry : map.entrySet()) {
            map.put(20,2);
            System.out.println(objectObjectEntry);
        }
    }

}
