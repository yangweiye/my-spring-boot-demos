package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.annotation.Content;
import com.yangweiye.springbootdemos.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootDemosApplication {
    @Autowired
    private User user;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootDemosApplication.class, args);
        User bean = run.getBean(User.class);
        System.out.println(bean);
        Content content = bean.getClass().getAnnotation(Content.class);
        String value = content.value();
        System.out.println(value); // [hello, world, annotation!]
    }
}
