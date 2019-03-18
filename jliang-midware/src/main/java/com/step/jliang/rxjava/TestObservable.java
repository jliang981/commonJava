package com.step.jliang.rxjava;

import com.google.common.collect.Lists;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author jliang
 */
public class TestObservable {

    private static final Scheduler SCHEDULER = Schedulers.computation();

    public static void main(String[] args) {

        testObservable();
        testWindow();
        testConnect();
        testMap();
        System.out.println("start wait---");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testWindow() {
        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS).subscribeOn(SCHEDULER)
                .window(2).subscribe((t) -> {
            System.out.println("receive Observable<T>" + t);
            t.subscribe(a -> System.out.println("----: " + a));
        });

    }

    private static void testConnect() {
        ConnectableObservable<Integer> publish = Observable.range(1, 5).publish();// .autoConnect(2)
        publish.subscribe(t -> System.out.println("--connect receive 1: " + t));
        publish.subscribe(t -> System.out.println("--connect receive 2: " + t));
        System.out.println("----connect---");
        publish.connect();
    }

    private static void testObservable() {
        Observable mObservable = Observable.fromCallable(() -> {
                System.out.println("1--------" + Thread.currentThread().getName());
                return 1;
        });

        Observer mObserver = new Observer<Object>() {
            //这是新加入的方法，在订阅后发送数据之前，
            //回首先调用这个方法，而Disposable可用于取消订阅
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(" onSubscribe start." + Thread.currentThread().getName());
            }

            @Override
            public void onNext(Object value) {
                System.out.println("observer receive: " + value + "------observer: " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("observer onError");
            }

            @Override
            public void onComplete() {
                System.out.println("observer onComplete");
            }
        };

//        mObservable.subscribeOn(SCHEDULER).subscribe(mObserver);
//        mObservable.subscribeOn(Schedulers.newThread()).observeOn(SCHEDULER).subscribe(mObserver);
        mObservable.subscribeOn(Schedulers.newThread())
                .observeOn(SCHEDULER)
                // 在SCHEDULER里执行
                .map(t -> t + " first map: " + Thread.currentThread().getName())
                .observeOn(Schedulers.newThread())
                .map(t -> t + " second map: " + Thread.currentThread().getName()) // 在Schedulers.newThread执行
                .doOnNext(t -> {
                    System.out.println("do on next: " + Thread.currentThread().getName());
                })
                .doFinally(() -> {
                    System.out.println("do finally.");
                }).subscribe(mObserver);

    }

    private static void testMap() {
        List<Integer> out = Lists.newArrayList();
        List<Integer> results = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("--------testMap------------");
        Flowable.fromIterable(results).observeOn(SCHEDULER)
                .map(t -> {
                    System.out.println("---testMap: -- " + Thread.currentThread().getName());
                    return t * 10;
                }).subscribe(t -> out.add(t));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("out : " + out);
    }

}
