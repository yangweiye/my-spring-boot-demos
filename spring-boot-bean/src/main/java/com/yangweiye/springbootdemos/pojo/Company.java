package com.yangweiye.springbootdemos.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Company {
    private User user;
    private Role role;

    public Company() {
        System.out.println("空餐构造" + user);

    }

    @Autowired
    public Company(@Qualifier("user") User user) {
        System.out.println("基于构造方法的 autowired user : " + user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    @Autowired
    public void setRole(Role role) {
        System.out.println("基于set方法的 autowired role : " + role);
        this.role = role;
    }

    @Override
    public String toString() {
        return "Company{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
