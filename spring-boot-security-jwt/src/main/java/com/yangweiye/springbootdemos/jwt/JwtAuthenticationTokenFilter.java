package com.yangweiye.springbootdemos.jwt;

import com.yangweiye.springbootdemos.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String uri = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader("Authorization");

        if (StringUtils.hasText(token) && token.startsWith("Bearer")) {
            token = token.substring("Bearer".length()).trim();
        }

        if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
            Authentication authentication = jwtUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.printf("set authentication %s uri %s\n", authentication.getPrincipal(), uri);
        } else {
            System.out.printf("invalid authentication uri %s\n", uri);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
