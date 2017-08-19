package com.mogujie.haoliang.cron4j;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.Date;

/**
 * corn4j这个工具最小的粒度精确到分钟，不能控制秒钟。
 */
public class Schdule {

    public static void main(String[] args) {
        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();
        // Schedule a once-a-minute task.
        s.schedule("* * * * *", new Runnable() {
            public void run() {
                System.out.println("Another minute ticked away..." + new Date().getTime());
                //sleep(1000);
            }
        });
        // Starts the scheduler.
        s.start();
        // Will run for ten minutes.
        try {
            Thread.sleep(1000L * 60L * 2L);
        } catch (InterruptedException e) {
            ;
        }
        // Stops the scheduler.
        s.stop();

        s.start();
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
