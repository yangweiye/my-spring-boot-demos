package com.yangweiye.springbootdemos.services.impl;

import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void printUser(User user) {
        System.out.println("id : " + user.getId());
        System.out.println("user name : " + user.getUserName());
    }
}
