package com.yangweiye.springbootdemos.dao;

import com.yangweiye.springbootdemos.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
