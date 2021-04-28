package com.yangweiye.springbootdemos.service;

import com.yangweiye.springbootdemos.pojo.User;

public interface UserService {
    User getUserInfoById(Long id);

    User getUserInfoByNickname(String nickname);
}
