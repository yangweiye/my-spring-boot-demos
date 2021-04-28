package com.yangweiye.springbootdemos.service.impl;

import com.yangweiye.springbootdemos.mapper.UserMapper;
import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService, ApplicationContextAware {
    @Autowired
    private UserMapper userMapper;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserInfo(Integer id) {
        return userMapper.getUserInfo(id);
    }

    @Override
    public User getUserInfoAnnotation(Long id) {
        return userMapper.getUserInfoAnnotation(id);
    }

    @Override
    public Integer insertUser(User user) {
        return userMapper.insert(user);
    }


    //@Transactional(propagation = Propagation.REQUIRED) //如果调用方有事物 且回滚 当前方法也会跟着回滚
    //@Transactional(propagation = Propagation.REQUIRES_NEW) // 调用方 完全无法干啥 当前方法
    @Transactional(propagation = Propagation.NESTED) // 沿用调用方的 隔离级别 锁 但 当前方法会有单独的事物
    public Integer insertUserTest(User user, int flag) {
        if (4 == flag)
            throw new RuntimeException("lalala");
        return userMapper.insert(user);
    }

    /*
        isolation 事物的隔离级别
            read uncommit 读未提交 可能出现的问题 脏读
            read commit 读已提交 可能出现的问题 不可重复读
            repeatable read 可重复读 可能出现的问题 幻读
            serialize 系列化 可能出现的问题 无
            隔离级别越高 数据可靠性越高， 但性能会变低

        propagation 事物的传播级别 spring boot 特有
        这里的有或者没有 都是值得调用方
            require 默认级别 有就用 没有就新建
            support 有就用 没有拉到
            mandatory 必须有 没有不行
            require new 爱有不有 我自建
            not support 爱有不有 我不用
            never 都不许有 大家都别用
            nest 嵌套 相当于 require new 但会沿用前事物的 隔离级别 锁
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void insertBatchUser() {
        UserServiceImpl bean = applicationContext.getBean(this.getClass());
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setUserName("test_" + i);
            user.setSex(1);
            user.setNote("");
            bean.insertUserTest(user, i);
        }

        throw new RuntimeException();
    }
}
