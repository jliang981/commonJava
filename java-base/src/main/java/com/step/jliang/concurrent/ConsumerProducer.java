package com.step.jliang.concurrent;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author jll
 */
public class ConsumerProducer {
    public static void main(String[] args) {
        SelfQueue selfqueue = new SelfQueue();

        //创建生产者线程和消费者线程
        Thread prodThread = new Thread(new Producer(selfqueue));
        Thread prodThread2 = new Thread(new Producer(selfqueue));
        Thread consThread = new Thread(new Consumer(selfqueue));
        Thread consThread2 = new Thread(new Consumer(selfqueue));

        //启动生产者线程和消费者线程
        prodThread.start();
        prodThread2.start();
        consThread.start();
        consThread2.start();
    }
}


class SelfQueue {
    int index = 0;
    int[] ProdLine = new int[6];

    public synchronized void produce(int ProdRandom) {
        while (index == ProdLine.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        ProdLine[index] = ProdRandom;
        index++;
        System.out.println("Produced: " + ProdRandom + " index: " + index);
    }

    public synchronized int consume() {
        while (index == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        index--;
        System.out.println("Consumed: " + ProdLine[index] + " index: " + index);
        return ProdLine[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(ProdLine);
    }
}

//生产者
class Producer implements Runnable {
    private final SelfQueue selfqueue;

    public Producer(SelfQueue selfqueue) {
        this.selfqueue = selfqueue;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int ProdRandom = random.nextInt(10);
            //      System.out.println("Produced: " + ProdRandom);
            selfqueue.produce(ProdRandom);
        }
    }
}

//消费者
class Consumer implements Runnable {
    private final SelfQueue selfqueue;

    public Consumer(SelfQueue selfqueue) {
        this.selfqueue = selfqueue;
    }

    public void run() {
        while (true) {
            selfqueue.consume();
//            System.out.println("selfqueue:" + selfqueue + "  Consumed: " + selfqueue.consume());
        }
    }
}