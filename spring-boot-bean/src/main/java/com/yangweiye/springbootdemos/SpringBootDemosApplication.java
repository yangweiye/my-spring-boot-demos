package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringBootDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemosApplication.class, args);

    }

    //定义Bean
    @Bean("user")
    @Profile("dev")
    public User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("yang");

        return user;
    }

    @Bean("user")
    @Profile("pro")
    public User getUser2() {
        User user = new User();
        user.setId(10L);
        user.setUserName("yangyang");

        return user;
    }

}
