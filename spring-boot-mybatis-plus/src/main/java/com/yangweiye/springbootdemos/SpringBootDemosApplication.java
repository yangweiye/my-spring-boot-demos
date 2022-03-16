package com.yangweiye.springbootdemos;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@SpringBootApplication
@MapperScan(annotationClass = Repository.class)
public class SpringBootDemosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemosApplication.class, args);
        /*FastAutoGenerator.create("jdbc:mysql://localhost:3306/blog", "root", "1998yang")
                .globalConfig(builder -> {
                    builder.author("yangweiye") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/yangweiye/JavaProjects/my-spring-boot-demos/spring-boot-mybatis-plus/src/main/generator/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.yangweiye.springbootdemos.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/yangweiye/JavaProjects/my-spring-boot-demos/spring-boot-mybatis-plus/src/main/generator/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("users")// 设置需要生成的表名
                            .addInclude("link");
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();*/

    }
}
