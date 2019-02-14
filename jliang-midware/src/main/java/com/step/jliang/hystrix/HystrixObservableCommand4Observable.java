package com.step.jliang.hystrix;

import rx.Observable;
import rx.Observer;

import java.io.IOException;

/**
 * @author jliang
 */
public class HystrixObservableCommand4Observable {

    public static void main(String[] args) {
        Observable<String> coldObservable = new HelloWorldHystrixObservableCommand("Hlx").toObservable();
//		TimeUnit.MILLISECONDS.sleep(2000);
//		System.out.println("22222222");


        // 注册观察者事件
        // subscribe()是非堵塞的
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        coldObservable.subscribe(new Observer<String>() {

            // 先执行onNext再执行onCompleted
            // @Override
            public void onCompleted() {
                System.out.println("coldObservable of ObservableCommand completed");
            }

            // @Override
            public void onError(Throwable e) {
                System.out.println("coldObservable of ObservableCommand error");
                e.printStackTrace();
            }

            // @Override
            public void onNext(String v) {
                System.out.println("coldObservable of ObservableCommand onNext: " + v);
            }

        });

        // 主线程不直接退出，在此一直等待其他线程执行
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
