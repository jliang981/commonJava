package com.step.jliang.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jliang
 */
public class ThreadPoolTest {

    private static ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS
            , new ArrayBlockingQueue(1), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " : run.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);

    }


}
