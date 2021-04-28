package com.yangweiye.springbootdemos.service;

import com.yangweiye.springbootdemos.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesByUserId(Long user_id);
}
