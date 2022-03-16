package com.yangweiye.springbootdemos.service;

import com.yangweiye.springbootdemos.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangweiye
 * @since 2022-01-13
 */
public interface IUsersService extends IService<Users> {
    List<Map> selectArticle();
}
