package com.step.jliang.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author jliang
 */
@Aspect
@Component
public class ServiceAspect {

    // 配置切点 service包及其子包下的所有方法
    @Pointcut("execution(* com.step.jliang.service..*.*(..))")
    public void executeService() {
    }

    // 配置切点 controller
    @Pointcut("execution(* com.step.jliang.web.controller.*.*(..))")
    public void executeControler() {
    }

    @Pointcut("executeControler() || executeService()")
    public void execute() {
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("execute()")
    public Object aroundService(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            Object object = joinPoint.proceed();
            return object;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            // 添加监控
            System.out.println(signature.getName() + " take time: " +
                    (System.currentTimeMillis() - start) + "ms");
        }
    }
}
