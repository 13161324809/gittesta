package com.wash.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.wash.car.mapper")
@EnableSwagger2
public class WashCarAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WashCarAdminApplication.class, args);
    }

}
