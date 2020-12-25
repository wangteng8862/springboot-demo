package com.kevin.demo.main;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@ComponentScan(basePackages = "com.kevin")
@EnableDubbo
public class BusinessApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApiApplication.class, args);
    }

}
