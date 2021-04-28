package com.yangweiye.springbootdemos.validator;

import com.yangweiye.springbootdemos.pojo.User;

public interface UserValidator {
    Boolean validator(User user);
}
