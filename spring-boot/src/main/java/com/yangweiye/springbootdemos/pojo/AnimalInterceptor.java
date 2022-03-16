package com.yangweiye.springbootdemos.pojo;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AnimalInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("enenen");
        methodProxy.invokeSuper(o, objects);
        System.out.println("刚过18岁");
        return null;
    }
}
