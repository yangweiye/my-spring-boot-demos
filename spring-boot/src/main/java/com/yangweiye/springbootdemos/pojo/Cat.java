package com.yangweiye.springbootdemos.pojo;

public class Cat implements Animal {
    @Override
    public void call() {
        System.out.println("喵喵喵~");
    }
}
