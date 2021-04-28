package com.yangweiye.springbootdemos.service.impl;

import com.yangweiye.springbootdemos.dao.UserJpaRepository;
import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public User getUserById(Long id) {
        Optional<User> res = userJpaRepository.findById(id);
        User user = res.get();
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> users = userJpaRepository.findAll();
        return users;
    }

    @Override
    public User insertUser(User user) {
        User save = userJpaRepository.save(user);
        return save;
    }

    @Override
    public User updateUser(User user) {
        User save = userJpaRepository.save(user);
        return save;
    }

    @Override
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }
}
