package com.yangweiye.springbootdemos.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getPrincipal().equals("123"))
            throw new UsernameNotFoundException("not found");
        //进行登录
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
