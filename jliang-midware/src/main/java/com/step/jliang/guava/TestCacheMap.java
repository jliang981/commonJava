package com.step.jliang.guava;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * @author haoliang
 * @Date 2019-04-01
 **/
public class TestCacheMap {

    public static void main(String[] args) throws Exception{
        RemovalListener<String, String> listener = (RemovalNotification<String, String> notification) -> {
            System.out.println(Thread.currentThread().getName() + " [" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
        };

        CacheLoader<String, String> loader = new CacheLoader<String, String> () {
            public String load(String key) throws Exception {
                Thread.sleep(1000); //休眠1s，模拟加载数据
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        };

        Cache<String, String> cache = CacheBuilder.newBuilder()
                // 缓存的大小，超过这个限制按照 lru 淘汰
                .maximumSize(2)
                // 写入后多久会被删除
                .expireAfterWrite(3, TimeUnit.SECONDS)
                // expireAfterAccess
                // 缓存里面的 key 和 value 都是弱引用，垃圾回收的时候，会被直接回收掉
//                .weakValues()
                // 只能监听到超出容量之后，而被删除的 key，
                // 不能监听到由于超时被删除的 key
                .removalListener(listener)
                // 增加缓存的统计信息
                .recordStats()
                // 可以在key不存在的时候，自动的加载
                // .build(loader)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        for (int i = 0; i < 5; i++) {
            System.out.println("第" + i + "次取到key2的值为：" + cache.getIfPresent("key2"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 当 key 不存在的时候，可以执行加载逻辑。
        System.out.println("key1: " + cache.get("key1", () -> {return "valueNew";}));
        System.out.println(cache.stats());
        Thread.sleep(5000);
    }

}
