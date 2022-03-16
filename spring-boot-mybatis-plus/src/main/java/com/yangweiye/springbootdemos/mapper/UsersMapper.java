package com.yangweiye.springbootdemos.mapper;

import com.yangweiye.springbootdemos.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yangweiye
 * @since 2022-01-13
 */
@Repository
public interface UsersMapper extends BaseMapper<Users> {
    List<Map> selectArticle();
}
