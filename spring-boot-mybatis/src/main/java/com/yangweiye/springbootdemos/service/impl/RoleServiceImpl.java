package com.yangweiye.springbootdemos.service.impl;

import com.yangweiye.springbootdemos.mapper.RoleMapper;
import com.yangweiye.springbootdemos.pojo.Role;
import com.yangweiye.springbootdemos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUserId(Long user_id) {
        return roleMapper.getRolesByUserId(user_id);
    }
}
