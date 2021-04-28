package com.yangweiye.springbootdemos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(annotationClass = Repository.class)
public class SpringBootDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemosApplication.class, args);
    }
}
