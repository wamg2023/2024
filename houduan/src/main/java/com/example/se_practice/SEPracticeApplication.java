package com.example.se_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.se_practice.dao")
public class SEPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SEPracticeApplication.class, args);
    }

}
