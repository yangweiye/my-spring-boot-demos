package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.pojo.Role;
import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.RoleService;
import com.yangweiye.springbootdemos.service.UserService;
import com.yangweiye.springbootdemos.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        log.info("hello");
        System.out.println(userService.getUserInfo(10));
    }

    @Autowired
    private RoleService roleService;

    @Test
    public void testMany() {
        List<Role> roles = roleService.getRolesByUserId(10L);
        System.out.println(roles);
    }

    @Test
    public void testAnnotation(){
        User userinfo = userService.getUserInfoAnnotation(10L);
        log.info(userinfo.toString());
    }

    @Autowired
    private UserServiceImpl userServiceimpl;
    @Test
    public void testBatchUser(){
        userServiceimpl.insertBatchUser();
    }

}
