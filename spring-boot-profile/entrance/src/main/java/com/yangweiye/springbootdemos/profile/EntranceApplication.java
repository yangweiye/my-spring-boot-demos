package com.yangweiye.springbootdemos.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EntranceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EntranceApplication.class, args);
        Test test = (Test) run.getBean("test");
        test.test();
    }
}
