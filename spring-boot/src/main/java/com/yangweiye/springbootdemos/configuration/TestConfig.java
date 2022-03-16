package com.yangweiye.springbootdemos.configuration;

import com.yangweiye.springbootdemos.pojo.CopyUser;
import com.yangweiye.springbootdemos.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public CopyUser tUser() {
        CopyUser user = new CopyUser();
        user.setNickName("aa");
        return user;
    }
}
