package com.yangweiye.springbootdemos.aspect;

import com.yangweiye.springbootdemos.validator.UserValidator;
import com.yangweiye.springbootdemos.validator.impl.UserValidatorImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class MyAspect {
    /*
        AOP术语 都以User UserService UserServiceImpl MyAspect 举例
        连接点 ： UserServiceImpl.printUser 这个方法 spring 只支持方法
        切点 ： 用于匹配的 语句
        通知 ： 按照约定实现的方法
        目标对象 ： 要被代理并增强的对象 UserService就是目标对象
        织入 ： 通过动态代理 将约定好的方法织入到目标对象
        引入 ： 引入新的类增强目标对象
        切面 ： 一个可以定义切点 通知 引入的内容
     */

    //这里引入了 UserValidator增强了 UserServiceImpl
    @DeclareParents(value = "com.yangweiye.springbootdemos.service.impl.UserServiceImpl", defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    //这是一个切点
    @Pointcut("execution(* com.yangweiye.springbootdemos.service.impl.UserServiceImpl.printUser(..))")
    private void pointcut() {
    }

    // before after afterReturning afterThrowing around 都是不同类型的通知
    @Before("pointcut()")
    private void before() {
        System.out.println("前置通知");
    }

    @After("pointcut()")
    private void after() {
        System.out.println("后置通知");

    }

    @AfterReturning("pointcut()")
    private void afterReturning() {
        System.out.println("后置返回通知");

    }

    @AfterThrowing("pointcut()")
    private void afterThrowing() {
        System.out.println("后置错误通知");

    }

    @Around("pointcut()")
    private void around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("前置环绕通知");
        try {
            proceedingJoinPoint.proceed();
            System.out.println("后置返回环绕通知");
        } catch (Throwable throwable) {
            System.out.println("后置错误环绕通知");
        } finally {
            System.out.println("后置环绕通知");
        }
    }
}
