package com.yangweiye.springbootdemos.mapper;

import com.yangweiye.springbootdemos.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from t_user where id = #{id}")
    User getUser(Long id);

    @Insert("insert into t_user (user_name,sex,note) values (#{userName},#{sex},#{note})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertUser(User user);

    @Delete("delete from t_user where id = #{id}")
    Integer deleteUser(Long id);
}
