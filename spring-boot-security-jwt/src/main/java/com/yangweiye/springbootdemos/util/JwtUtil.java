package com.yangweiye.springbootdemos.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class JwtUtil {
    @Autowired
    private UserDetailsService userDetailsService;
    private Key key;

    public JwtUtil() {
        byte[] bytes = Base64.getDecoder().decode("cereshuzhitingnizhenbangcereshuzhitingnizhenbang");
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createToken(Integer user_id, String nickname) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user_id);
        claims.put("nickname", nickname);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date issueDate = calendar.getTime();
        calendar.add(Calendar.MINUTE, 10);
        Date expireDate = calendar.getTime();

        String jwt = Jwts.builder()
                .addClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compressWith(CompressionCodecs.DEFLATE)
                .compact();

        return jwt;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = this.getClaimsFromToken(token);
        String nickname = (String) claims.get("nickname");
        UserDetails userDetails = userDetailsService.loadUserByUsername(nickname);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), token, userDetails.getAuthorities());
        authentication.setDetails(userDetails);

        return authentication;
    }
}
