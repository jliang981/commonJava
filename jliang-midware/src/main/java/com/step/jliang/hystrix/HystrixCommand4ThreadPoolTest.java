package com.step.jliang.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 设置线程池里的线程数＝3，然后循环>3次和<3次，最后查看当前所有线程名称
 */
public class HystrixCommand4ThreadPoolTest extends HystrixCommand<String> {

    private final String name;

    public HystrixCommand4ThreadPoolTest(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolTest"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(1500)
                                .withExecutionIsolationThreadInterruptOnTimeout(true)
                                .withCircuitBreakerErrorThresholdPercentage(50)
                                .withCircuitBreakerRequestVolumeThreshold(5)
                )
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(3)// 配置线程池里的线程数
                                .withMaxQueueSize(20)
                                .withQueueSizeRejectionThreshold(20)
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("====command run : " + Thread.currentThread().getName() + " at: " + System.currentTimeMillis());
        TimeUnit.MILLISECONDS.sleep(3000);
//        throw  new RuntimeException("=== timeout ...");
        return name;

    }

    @Override
    protected String getFallback() {
        return "fallback: " + name + Thread.currentThread().getName() + " at : " + System.currentTimeMillis();
    }

    public static class UnitTest {

        @Test
        public void testSynchronous() throws IOException {
            for (int i = 0; i < 10; i++) {
                try {
                    new Thread(() -> System.out.println("===========" + new HystrixCommand4ThreadPoolTest("Hlx").execute())).start();
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
                }
            }

            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (Exception e) {
            }

            for (int i = 0; i < 10; i++) {
                try {
                    new Thread(() -> System.out.println("===========" + new HystrixCommand4ThreadPoolTest("Hlx").execute())).start();
//                    futures.add(new HystrixCommand4ThreadPoolTest("Hlx"+i).queue());
                } catch (Exception e) {
                    System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
                }
            }

//            System.out.println("------开始打印现有线程---------");
//            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
//            for (Thread thread : map.keySet()) {
//                System.out.println(thread.getName());
//            }
//            System.out.println(map);
//            System.out.println("thread num: " + map.size());
//        	int numExecuted = HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().size();
//            System.out.println("num executed: " + numExecuted);
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}