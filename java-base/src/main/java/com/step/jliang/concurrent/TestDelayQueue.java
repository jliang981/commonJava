package com.step.jliang.concurrent;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * java提供的DelayQueue只有在take的时候，才会发生阻塞。
 * 因为内部使用的优先队列存储数据，优先队列是无限容量的，因此offer数据的时候，是肯定会成功的。
 * 这种基于内存的队列没办法实现持久化
 * <p>
 * 考虑两种场景：
 * 1. 想要限制延迟队列的大小
 * 2. offer的时候，如果没有可用空间，阻塞当前线程。
 *
 * @author haoliang
 * @Date 2019-05-01
 **/
public class TestDelayQueue {

    public static void main(String[] args) {
        BoundDelayQueue<DelayTask> queue = new BoundDelayQueue<>();
        new Thread(() -> {
//                Thread.sleep(3000);
                while (true) {
                    try {
                        DelayTask task = queue.take();
                        if (task != null) {
                            System.out.println(task.getContent());
                        } else {
                            Thread.sleep(100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();
        for (int i = 1; i < 10; i++) {
            queue.offer(new DelayTask(i * 1000, String.valueOf(i)));
        }
    }

    static class DelayTask implements Delayed {
        private long delay;
        @Getter
        private String content;
        private long runtime;

        DelayTask(long delay, String content) {
            this.delay = delay;
            this.content = content;
            runtime = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runtime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == this)
                return 0;
            return Long.compare(getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
