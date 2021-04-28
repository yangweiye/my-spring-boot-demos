package com.yangweiye.springbootdemos.service.impl;

import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import org.springframework.stereotype.Service;

//这个类是目标对象
@Service
public class UserServiceImpl implements UserService {
    //这个方法就是连接点
    @Override
    public void printUser(User user) {
        if (null == user) {
            throw new RuntimeException("参数不能为空");
        }
        System.out.println("id : " + user.getId());
        System.out.println("user name : " + user.getUserName());
    }
}
