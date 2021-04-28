package com.yangweiye.springbootdemos.dao;

import com.yangweiye.springbootdemos.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    @Select("select * from ms_role where id = #{id}")
    Role getRoleById(Long id);

    @Select("select r.* from ms_user_role_relation as ur left join ms_role as r on ur.role_id = r.id where ur.user_id = #{user_id}")
    List<Role> getRolesByUserId(Long user_id);

    @Insert("insert into ms_role (role_name) values(#{roleName})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertRole(Role role);

}
