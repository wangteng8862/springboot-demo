package com.kevin.demo.main.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.utils.ContentUtils;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.management.resources.agent;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2021/1/4 15:17
 */
@NacosPropertySource(dataId = "dbConfig.properties")
@Component
@Slf4j
public class NacosConfigBean {
    @NacosValue(value = "${config}")
    private String dbConfig;

    @Value("${nacos.config.server-addr}")
    private String server;

    public String getDBConfig() {
        return dbConfig;
    }

    @PostConstruct
    private void initProducer() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(
                1,
                new BasicThreadFactory
                        .Builder()
                        .namingPattern("com.kevin.demo.nacos.config.worker-"+server)
                        .daemon(true)
                        .build());

        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    getDbConfigBySDK();
                } catch (Throwable e) {
                    log.error("[" + server + "] [data-received] rotate check error", e);
                }
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

    public void getDbConfigBySDK() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", server);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig("dbConfig.properties", "DEFAULT_GROUP", 5000);
        dbConfig = content.split("=")[1];

        log.info("[{}] [data-received] dataId={}, group={}, content={}", Thread.currentThread().getName(), "dbConfig.properties", "DEFAULT_GROUP", dbConfig);
    }

}
