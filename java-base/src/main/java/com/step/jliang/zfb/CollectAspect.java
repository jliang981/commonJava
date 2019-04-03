package com.step.jliang.zfb;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author haoliang
 * @Date 2019-03-28
 **/
public class CollectAspect {

    private static ConcurrentHashMap<String, AtomicLong> collectMap = new ConcurrentHashMap<>();
    // 线程池异步上报桶里的数据到 hashmap 中
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    // 桶
    private static int[] time = new int[100];

    @Pointcut("execution(public * com.step.jliang..*.*(..)) " +
            "&& @within(com.step.jliang.RpcCollect)")
    private void collect() {

    }

    @Around("collect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            // 提交到当前时间对应的桶。
            Object object = joinPoint.proceed();
            return object;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }
}