package com.yangweiye.springbootdemos.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class User {
    private Long id;
    private String nickname;
    private String password;
    private String head;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date registerTime;
    private Date lastLoginTime;
    private Integer loginCount;
    private List<Role> roles;

    public User() {
    }

    public User(Long id, String nickname, String password, String head, Date registerTime, Date lastLoginTime, Integer loginCount, List<Role> roles) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.head = head;
        this.registerTime = registerTime;
        this.lastLoginTime = lastLoginTime;
        this.loginCount = loginCount;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", head='" + head + '\'' +
                ", register_time=" + registerTime +
                ", last_login_time=" + lastLoginTime +
                ", login_count=" + loginCount +
                ", roles=" + roles +
                '}';
    }
}
