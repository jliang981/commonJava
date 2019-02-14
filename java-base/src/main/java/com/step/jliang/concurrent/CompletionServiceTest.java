package com.step.jliang.concurrent;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jliang
 */
public class CompletionServiceTest {

    public static void main(String[] args) {
        int taskSize = 6;
        ExecutorService executor = Executors.newFixedThreadPool(taskSize);
        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);

        Runnable runnable = () -> {
            try {
                int st = new Random().nextInt(1000);
                Thread.sleep(st);
                System.out.println(String.format("%s sleep %d.", Thread.currentThread().getName(), st));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < taskSize; i++) {
            completionService.submit(runnable, i);
        }

        try {
            for (int i = 0; i < taskSize; i++) {
                System.out.println(completionService.take().get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
