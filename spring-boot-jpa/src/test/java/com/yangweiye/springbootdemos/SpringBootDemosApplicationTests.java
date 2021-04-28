package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.enumeration.SexEnum;
import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootDemosApplicationTests {
    private static Logger log = LoggerFactory.getLogger(SpringBootDemosApplication.class);

    @Autowired
    ApplicationContext app;

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        //默认数据源是 hikari 这里使用了 druid
        log.info(dataSource.getClass().getName());
        User user = userService.getUserById(10L);
        System.out.println(user);
        List<User> userList = userService.getUserList();
        userList.forEach(System.out::println);
        userService.updateUser(new User(11L, "我是58", SexEnum.MALE, "有啥好说的"));
        userList = userService.getUserList();
        userList.forEach(System.out::println);
    }

}
