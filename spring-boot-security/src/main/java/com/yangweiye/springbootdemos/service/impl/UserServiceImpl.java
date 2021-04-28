package com.yangweiye.springbootdemos.service.impl;

import com.yangweiye.springbootdemos.dao.UserMapper;
import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoById(Long id) {
        return userMapper.getUserInfoById(id);
    }

    @Override
    public User getUserInfoByNickname(String nickname) {
        return userMapper.getUserInfoByNickname(nickname);
    }
}
