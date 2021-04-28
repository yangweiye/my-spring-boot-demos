package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import com.yangweiye.springbootdemos.validator.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

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
        /*
            UserServiceImpl 被两个 AOP增强
                MyAspect
                SecondAspect
            MyAspect 还引入了 UserValidator
         */
        User user = new User();
        user.setId(1L);
        user.setUserName("yangweiye");
        UserValidator userValidator = (UserValidator) userService;
        if (userValidator.validator(user)) {
            userService.printUser(user);
        } else {
            log.info("user is null");
        }

    }

}
