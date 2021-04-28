package com.yangweiye.springbootdemos.service;

import com.yangweiye.springbootdemos.pojo.User;

public interface UserService {
    User getUserById(Integer id);

    User getUserInfo(Integer id);

    User getUserInfoAnnotation(Long id);

    Integer insertUser(User user);
}
