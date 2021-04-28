package com.yangweiye.springbootdemos.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(2)
@Component
public class SecondAspect {
    @Around("execution(* com.yangweiye.springbootdemos.service.impl.UserServiceImpl.printUser(..))")
    private void around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("我是order2 的环绕通知 我后执行");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
