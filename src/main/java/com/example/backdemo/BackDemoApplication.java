package com.example.backdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan(basePackages = "com.example.backdemo.mapper")
//扫描所需要的包，包含一些自用的工具类包，所在的路径
@ComponentScan(basePackages = "com.example.backdemo")
public class BackDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackDemoApplication.class, args);
    }

}

