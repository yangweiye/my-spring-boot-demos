package com.yangweiye.springbootdemos.validator.impl;

import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
    @Override
    public Boolean validator(User user) {
        System.out.println("引入新的接口");
        return null != user;
    }
}
