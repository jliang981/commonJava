package com.step.jliang.concurrent;

import java.util.concurrent.*;

public class TestCompletableFuture {

    public static ExecutorService executorService = new ThreadPoolExecutor(4, 4,
            100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    public static void main(String[] args) {
        consume();
        thenCombine();
        applyToEither();
    }

    public static void consume() {
        CompletableFuture.supplyAsync(() -> {
            //长时间的计算任务
//            int i = 1/0;
            return "·00";
        }, executorService)
                .whenCompleteAsync((v, e) -> {
                    v += "11";
                    System.out.println(v + " " + Thread.currentThread().getName());
                    System.out.println(e);
                })
                .whenComplete((v, e) -> {
                    System.out.println(v + " " + Thread.currentThread().getName());
                    System.out.println(e);
                }).handle((s, e) -> "handle " + s)
                .thenApply(s -> "thenApply " + s)
                .thenAccept(s -> System.out.println(s));
    }

    public  static void thenCombine() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("first future");
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("second future");
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }

    public static void applyToEither() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), s -> s).join();
        System.out.println(result);
    }
}
