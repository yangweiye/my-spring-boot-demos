package com.yangweiye.springbootdemos.service;

import com.yangweiye.springbootdemos.pojo.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> getUserList();

    User insertUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);
}
