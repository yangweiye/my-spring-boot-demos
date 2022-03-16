package com.yangweiye.springbootdemos.profile;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Test implements ApplicationContextAware {
    @Value("${p1.name}")
    private String p1name;

    @Value("${name}")
    private String name;

    @Value("${data.l2}")
    private String l2;

    @Value("${data.l3}")
    private String l3;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("aa");
    }

    public void test() {
        System.out.println(p1name);
        System.out.println(name);
        System.out.println(l2);
        System.out.println(l3);
    }
}
