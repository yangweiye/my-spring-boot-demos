package com.yangweiye.springbootdemos.service.impl;

import com.yangweiye.springbootdemos.pojo.Role;
import com.yangweiye.springbootdemos.pojo.User;
import com.yangweiye.springbootdemos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserInfoByNickname(s);
        if (null == user) {
            throw new UsernameNotFoundException("username not found!");
        }
        String username = user.getNickname();
        String password = user.getPassword();

        //是否可用
        Boolean enabled = true;
        //帐号 未 到期
        Boolean accountNonExpired = true;
        //证书（密码） 未 到期
        Boolean credentialsNonExpired = true;
        //帐号 未 锁定
        Boolean accountNonLocked = true;

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach((Role role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        return userDetail;
    }
}
