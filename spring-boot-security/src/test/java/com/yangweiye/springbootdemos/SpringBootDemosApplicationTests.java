package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import com.yangweiye.springbootdemos.service.impl.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootDemosApplicationTests {
    private static Logger log = LoggerFactory.getLogger(SpringBootDemosApplication.class);

    @Autowired
    ApplicationContext app;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setNickname("yangweiye");
        user.setPassword("123456");
        user.setHead("none");
        user.setLoginCount(0);
        user.setRegisterTime(new Date());
        user.setLastLoginTime(new Date());
        System.out.println(user);
        log.info("hello");
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void tt() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

}
