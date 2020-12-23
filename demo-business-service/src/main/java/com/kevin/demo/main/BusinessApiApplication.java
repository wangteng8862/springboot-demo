package com.kevin.demo.main;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
        "com.kevin.demo"
})
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
@EnableDubbo
@EnableNacosDiscovery
public class BusinessApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApiApplication.class, args);
    }

}
