package com.office.notfound.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.office.notfound")
@MapperScan(basePackages = "com.office.notfound")
public class NotfoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotfoundApplication.class, args);
    }

}
