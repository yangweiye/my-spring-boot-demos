package com.yangweiye.springbootdemos.pojo;

import com.yangweiye.springbootdemos.annotation.Content;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public class CopyUser implements Serializable {
    private String nickName;
    private Integer age;
    private Integer gender;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public static class testUser {
        public static void pp() {
            System.out.print("i am user -> testUser -> pp");
        }
    }
}
