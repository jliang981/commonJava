package com.step.jliang.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author haoliang
 * @Date 2019-05-01
 **/
public class BoundDelayQueue<E extends Delayed> extends DelayQueue<E> {
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private final int MAX_SIZE = 5;

    @Override
    public boolean offer(E o) {
        if (o == null) {
            return false;
        }
        try {
            lock.lockInterruptibly();
            while (size() >= MAX_SIZE) {
                notFull.await();
            }
            return super.offer(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            E element = super.poll();
            if (element != null) {
                notFull.signal();
            }
            return element;
        } finally {
            lock.unlock();
        }
    }

}
