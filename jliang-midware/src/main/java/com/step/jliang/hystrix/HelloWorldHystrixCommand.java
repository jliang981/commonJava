package com.step.jliang.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author jliang
 */
public class HelloWorldHystrixCommand extends HystrixCommand {
    private final String name;

    public HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    // 如果继承的是HystrixObservableCommand，要重写Observable construct()
    @Override
    protected String run() throws InterruptedException {
        return "Hello " + name + "! thread:" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        System.out.println("触发了降级!");
        return "exeucute fallback";
    }

    public static void main(String[] args) {
        /* 调用程序对HelloWorldHystrixCommand实例化，执行execute()即触发HelloWorldHystrixCommand.run()的执行 */
        Object result = new HelloWorldHystrixCommand("HLX").execute();
        System.out.println(result);  // 打印出Hello HLX
    }
}
