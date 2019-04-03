package com.step.jliang.helper;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haoliang
 * @Date 2019-03-25
 **/
public class SelectionHelper {

    private static AtomicInteger threadNum = new AtomicInteger(1);

    private ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<>(50),
            (r) -> {
                return new Thread(r, "SelectionHelperPool" + threadNum.getAndIncrement());
            }, new ThreadPoolExecutor.CallerRunsPolicy());

    public void build() {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println("xxx");
            });
        }
    }

}
