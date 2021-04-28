package com.yangweiye.springbootdemos.pojo;


import com.yangweiye.springbootdemos.converter.SexConverter;
import com.yangweiye.springbootdemos.enumeration.SexEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Convert(converter = SexConverter.class)
    private SexEnum sex;
    private String note;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_relation", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roleList;

    public User() {
    }

    public User(Long id, String userName, SexEnum sex, String note) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", note='" + note + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
