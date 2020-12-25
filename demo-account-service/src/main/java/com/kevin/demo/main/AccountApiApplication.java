package com.kevin.demo.main;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@ComponentScan(basePackages = "com.kevin")
@MapperScan(basePackages = {"com.kevin.demo.dao"})
@EnableDubbo
public class AccountApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApiApplication.class, args);
    }

}
