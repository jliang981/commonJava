package com.step.jliang.concurrent;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + "try acquire");
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "acquired semaphore");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        // 没有线程获取信号量的时候，仍然能够释放。release 并没有规定 acquire 的线程才能释放。并且释放的信号量会累加到有效的计数里
        semaphore.release();

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }

    }

}
