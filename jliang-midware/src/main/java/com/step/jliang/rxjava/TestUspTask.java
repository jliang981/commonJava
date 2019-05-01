package com.step.jliang.rxjava;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import rx.observables.BlockingObservable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestUspTask {

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(20, new ThreadFactory() {
        private final AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "taskThread-" + this.count.getAndIncrement());
        }
    });
    private static final Scheduler SCHEDULER = Schedulers.from(EXECUTOR);

    public static void main(String[] args) throws Exception {
        List<Task> taskList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            taskList.add(new Task());
        }
        Map<String, List<Object>> resultMap = Maps.newHashMap();
        Semaphore semaphore = new Semaphore(1);
        // 通过信号量来实现处理完成通知主线程的机制
        semaphore.acquire();
        // observeOn只会绑定一个线程，这个Observable里面的所有元素的后续动作，只会在这个线程上执行。
        Observable.fromIterable(taskList).subscribeOn(Schedulers.newThread()).observeOn(SCHEDULER)
                .map(task -> {
                    // 任务最好使用 try catch 包装，防止出错后影响其他任务。
                    System.out.println(Thread.currentThread().getName() + " run map ");
                    task.execute();
                    return task;
                })   //.blockingForEach((task) -> {})
                .subscribe(task -> {
                    System.out.println(Thread.currentThread().getName() + " consume task.");
                    task.getResult().forEach((k, v) -> {
                        if (resultMap.containsKey(k)) {
                            resultMap.get(k).add(v);
                        } else {
                            resultMap.put(k, Lists.newArrayList(v));
                        }
                    });
                },Functions.ON_ERROR_MISSING, semaphore::release);

        semaphore.acquire();
// 如果 usp 里面使用 blockingForecah 是有问题的，比如一个 task 抛出了异常，执行就结束了，不能确定其他 task 又没有执行完成。
//        Thread.sleep(1000);
//        Map<String, List<Object>> resultMap = Maps.newHashMap();
//        for (Task task : taskList) {
//            task.getResult().forEach((k, v) -> {
//                if (resultMap.containsKey(k)) {
//                    resultMap.get(k).add(v);
//                } else {
//                    resultMap.put(k, Lists.newArrayList(v));
//                }
//            });
//        }
        System.out.println(resultMap);

    }

    private static class Task {
        @Getter
        private Map<String, Object> result;

        public void execute() {
            Map<String, Object> map = Maps.newHashMap();
            for (int i = 0; i < 3; i++) {
                int key = ThreadLocalRandom.current().nextInt(5);
                System.out.println(Thread.currentThread().getName() + " run task.key: " + key + " value: " + i);
                map.put(String.valueOf(key), String.valueOf(i));
            }
            result = map;
        }
    }
}
