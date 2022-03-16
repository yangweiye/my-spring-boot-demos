package com.yangweiye.springbootdemos.service.impl;

import com.yangweiye.springbootdemos.entity.Users;
import com.yangweiye.springbootdemos.mapper.UsersMapper;
import com.yangweiye.springbootdemos.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangweiye
 * @since 2022-01-13
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Autowired
    private UsersMapper usersMapper;

    public List<Map> selectArticle() {
        return usersMapper.selectArticle();
    }
}
