package com.yangweiye.springbootdemos.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("role")
public class Role {
    @Value("1")
    private Long id;
    @Value("经理")
    private String position;
    @Value("11")
    private Integer level;

    public Role() {
    }

    public Role(Long id, String position, Integer level) {
        this.id = id;
        this.position = position;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", level=" + level +
                '}';
    }
}
