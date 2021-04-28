package com.yangweiye.springbootdemos.pojo;

import com.yangweiye.springbootdemos.condition.CustomizedCondition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Conditional(CustomizedCondition.class)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

public class Customized implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private String name;

    public Customized() {
    }

    public Customized(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("1.获取到 BeanName" + s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("2.获取到 BeanFactory" + beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("3.获取到 applicationContext" + applicationContext);
    }

    @PostConstruct
    public void construct() {
        System.out.println("4.自身的构造函数");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5.Bean 的构造函数");
    }

    @PreDestroy
    public void destruct() {
        System.out.println("6.自身的析构函数");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("7.Bean 的析构函数");
    }
}
