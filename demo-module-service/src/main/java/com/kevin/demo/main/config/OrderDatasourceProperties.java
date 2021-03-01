package com.kevin.demo.main.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.order")
public class OrderDatasourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}

