package com.yangweiye.springbootdemos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String header;
    private String tokenStartWith;
    private String base64Secret;
    private Integer tokenValidityInSeconds;

    public JwtConfig() {
    }

    public JwtConfig(String header, String tokenStartWith, String base64Secret, Integer tokenValidityInSeconds) {
        this.header = header;
        this.tokenStartWith = tokenStartWith;
        this.base64Secret = base64Secret;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTokenStartWith() {
        return tokenStartWith;
    }

    public void setTokenStartWith(String tokenStartWith) {
        this.tokenStartWith = tokenStartWith;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public Integer getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public void setTokenValidityInSeconds(Integer tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    @Override
    public String toString() {
        return "JwtConfig{" +
                "header='" + header + '\'' +
                ", tokenStartWith='" + tokenStartWith + '\'' +
                ", base64Secret='" + base64Secret + '\'' +
                ", tokenValidityInSeconds=" + tokenValidityInSeconds +
                '}';
    }
}
