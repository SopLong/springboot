package com.example.backdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan(basePackages = "com.example.backdemo.mapper")
@ComponentScan(basePackages = "com.example.backdemo")
public class BackDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackDemoApplication.class, args);
    }

}

