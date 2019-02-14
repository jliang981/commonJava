package com.step.jliang.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author jliang
 */
public class HystrixCommand4Semphore extends HystrixCommand<String> {
    private final String name;

    public HystrixCommand4Semphore(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemphoreTestGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(1500)
                                .withCircuitBreakerErrorThresholdPercentage(50)
                                .withCircuitBreakerRequestVolumeThreshold(5)
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(5)
                )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("====command run : " + Thread.currentThread().getName() + " at: " + System.currentTimeMillis());
        TimeUnit.MILLISECONDS.sleep(3000);
        return name;

    }

//    @Override
//    protected String getFallback() {
//        return "semphore fallback: " + name + Thread.currentThread().getName() + " at : " + System.currentTimeMillis();
//    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                new Thread(() -> System.out.println("===========" + new HystrixCommand4Semphore("Hlx").execute())).start();
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("run()抛出HystrixBadRequestException时，被捕获到这里" + e.getCause());
            }
        }
    }
}
