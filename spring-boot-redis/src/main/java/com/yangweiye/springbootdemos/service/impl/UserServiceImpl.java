package com.yangweiye.springbootdemos.service.impl;

import com.yangweiye.springbootdemos.mapper.UserMapper;
import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Cacheable(cacheNames = "redisCache", key = "'redis_user_'+#id")
    @Override
    public User getUser(Long id) {
        System.out.println("走到了查询");
        return userMapper.getUser(id);
    }

    // 调用该方法时 会将 返回结果写入cache
    @CachePut(cacheNames = "redisCache", key = "'redis_user_'+#result.id")
    @Override
    public User insertUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    //在方法执行后 返回结果大于0 删除cache
    @CacheEvict(cacheNames = "redisCache", key = "'redis_user_'+#id", condition = "#result > 0", beforeInvocation = false)
    @Override
    public Integer deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }
}
