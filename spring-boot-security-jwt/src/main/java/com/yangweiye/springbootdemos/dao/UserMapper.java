package com.yangweiye.springbootdemos.dao;

import com.yangweiye.springbootdemos.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from ms_user where id = #{id}")
    @Results({
            @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "id", property = "roles", many = @Many(select = "com.yangweiye.springbootdemos.dao.RoleMapper.getRolesByUserId"))
    })
    User getUserInfoById(Long id);

    @Insert("insert into ms_user (nickname,password,head,register_time,last_login_time,login_count) values (#{nickname},#{password},#{head},#{register_time},#{last_login_time},#{login_count})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertUser(User user);

    @Insert("insert into ms_user_role_relation (user_id,role_id) values (#{user_id},#{role_id})")
    Integer insertUserRoleRelation(Long user_id, Long role_id);

    @Select("select * from ms_user where nickname = #{nickname}")
    @Results({
            @Result(column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(column = "id", property = "roles", many = @Many(select = "com.yangweiye.springbootdemos.dao.RoleMapper.getRolesByUserId"))
    })
    User getUserInfoByNickname(String nickname);
}
