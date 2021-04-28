package com.yangweiye.springbootdemos.service;

import com.yangweiye.springbootdemos.pojo.User;

public interface UserService {
    User getUser(Long id);

    User insertUser(User user);

    Integer deleteUser(Long id);
}
