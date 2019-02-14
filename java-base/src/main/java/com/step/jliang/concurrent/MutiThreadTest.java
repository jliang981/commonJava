package com.step.jliang.concurrent;

import java.util.HashMap;

/**
 * @author jliang
 */
public class MutiThreadTest {

    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2, 0.75f);

    private String flag = "true";

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main Thread Run!");
        MutiThreadTest test = new MutiThreadTest();
        NotifyThread notifyThread = test.new NotifyThread("notify01");
        WaitThread waitThread01 = test.new WaitThread("waiter01");
        WaitThread waitThread02 = test.new WaitThread("waiter02");
        WaitThread waitThread03 = test.new WaitThread("waiter03");
        notifyThread.start();
        waitThread01.start();
        waitThread02.start();
        waitThread03.start();

    }


    class NotifyThread extends Thread {
        public NotifyThread(String name) {
            super(name);
        }

        public void run() {
            try {
                sleep(3000);// 推迟3秒钟通知
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("NotifyThread before lock");
            synchronized (flag) {
                flag.notifyAll();
                System.out.println("NotifyThread after lock");
            }
        }
    }

    ;

    class WaitThread extends Thread {
        public WaitThread(String name) {
            super(name);
        }

        public void run() {
            System.out.println(getName() + "  flag:" + flag);
            synchronized (flag) {
//                while (!flag.equals("false")) {
                System.out.println(getName() + " begin waiting!");
                long waitTime = System.currentTimeMillis();

                try {
                    flag.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitTime = System.currentTimeMillis() - waitTime;
                System.out.println("wait time :" + waitTime);
//                }
                System.out.println(getName() + " end waiting!");
            }
        }
    }
}
