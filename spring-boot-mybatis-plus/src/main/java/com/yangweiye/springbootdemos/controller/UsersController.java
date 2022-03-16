package com.yangweiye.springbootdemos.controller;


import com.yangweiye.springbootdemos.entity.Users;
import com.yangweiye.springbootdemos.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yangweiye
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @GetMapping("/list")
    public List<Users> userList() {
        List<Users> list = usersService.list();
        return list;
    }

    @GetMapping("/article")
    public List<Map> article() {
        List<Map> map = usersService.selectArticle();
        return map;
    }
}
