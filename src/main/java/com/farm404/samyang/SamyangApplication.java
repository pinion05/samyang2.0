package com.farm404.samyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.farm404.samyang.mapper")
public class SamyangApplication {
    public static void main(String[] args) {
        SpringApplication.run(SamyangApplication.class, args);
    }
}