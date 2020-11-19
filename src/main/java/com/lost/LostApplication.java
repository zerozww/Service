package com.lost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan(basePackages = "com.lost.mapper")
public class LostApplication {

    public static void main(String[] args) {
        SpringApplication.run(LostApplication.class, args);
    }

}
