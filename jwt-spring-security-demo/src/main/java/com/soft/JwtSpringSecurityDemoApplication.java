package com.soft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.soft.mapper")
public class JwtSpringSecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringSecurityDemoApplication.class, args);
    }

}
