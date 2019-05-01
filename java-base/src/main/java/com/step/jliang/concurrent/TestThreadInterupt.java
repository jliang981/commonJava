package com.step.jliang.concurrent;

/**
 * @author haoliang
 * @Date 2019-04-12
 **/
public class TestThreadInterupt {

    private volatile static boolean flag = false;

    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("running");
                    if (flag) {
                        Thread.sleep(3000);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        thread1.start();
        thread1.interrupt();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
    }

}
