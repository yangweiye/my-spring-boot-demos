package com.yangweiye.springbootdemos.pojo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TargetInvoker implements InvocationHandler {
    private Object target;

    public TargetInvoker(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object invoke = method.invoke(target, args);
        System.out.println("after");

        return invoke;
    }
}
