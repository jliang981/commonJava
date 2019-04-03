package com.step.jliang.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 内存缓存，先把数据缓存起来，用于批量操作。比如批量同步 es 等。
 *
 * 每个应用一个Router，跟appName绑定，因为不同的应用，需要获取的es客户端不一样。
 *
 * buffer.add方法：先获取ReentrantLock把数据添加到concurrentLinkedQueue中，但是添加之前会判断队列是否已经满了，size>=1000.
 * 如果已经满了等待notFull条件。因为要等待condition，必须先获得锁。
 *
 * ESBatchAddRunner： 每个add里面的数据肯定属于同一个app下的。每100个文档构成bulkrequest，提交到es。记录每一个文档的成功失败情况，
 * 并且记录到sentry。对于ESrejectException系统自动重试5次，如果还是失败的，备份到corgi，人工处理。
 *
 * Monitor：守护线程，10ms执行一次，判断队列是否满了，或者是否已经超时了。如果满足任何一种情况，需要处理（判断是否需要处理的时候不需要获取锁）。
 * 处理的时候，为了降低对生产端的影响，不会直接对这个队列处理，而是把这个队列copy下来，提交到线程池，然后新建一个空队列返回给生产者。并且通知
 * 生产者（NotFull）。每100个文档生成一个任务ESBatchAddRunner，提交到线程池，如果线程池满了，则等待。这个时候生产者也不能生产。
 *
 * 注册虚拟机shutdown钩子函数，当关闭jvm时，处理现在已经在队列中的数据，等待线程池关闭。
 */
public class SteplogMemoryBuffer {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();
    // 钩子函数开关，jvm 关闭的时候，先把内存的数据处理完毕
    private volatile boolean stopping = false;

    private static volatile List<String> messageList = new LinkedList<>();
    private static volatile AtomicInteger count = new AtomicInteger(0);
    private static final int size = 1000;

    public static void add(String s) {
        try {
            lock.lock();
            if (messageList.size() >= size) {
                notFull.await();
            }
            messageList.add(s);
            if (count.getAndIncrement() >= size) {
                notEmpty.notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private class Monitor extends Thread {
        @Override
        public void run() {
            while (!stopping) {
                lock.lock();
                try {
                    notEmpty.await(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!messageList.isEmpty()) {

                }
            }
//            logger.info("ESBulkBuffer Monitor for [{}] Stopped...", appName);
        }
    }

}
