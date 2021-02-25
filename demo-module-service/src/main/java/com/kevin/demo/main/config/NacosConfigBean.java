package com.kevin.demo.main.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
@Component
@NacosPropertySource(dataId = "dbConfig.properties")
@NacosPropertySource(dataId = "warehouseConfig.properties")
@NacosPropertySource(dataId = "bizConfig.properties")
@Slf4j
public class NacosConfigBean {
    @Value(value = "${config}")
    private String dbConfig;

    @NacosValue(value = "${warhouse_bj}")
    private Long warhouseBJ;

    @NacosValue(value = "${warhouse_tj}")
    private Long warhouseTJ;

    @NacosValue(value = "${biz_storage}")
    private Long bizStorage;

    @NacosValue(value = "${biz_order}")
    private Long bizOrder;

    @Value("${nacos.config.server-addr}")
    private String server;

    public String getDBConfig() {
        return dbConfig;
    }

    public Long getWarhouseBJ() {
        return warhouseBJ;
    }

    public Long getWarhouseTJ() {
        return warhouseTJ;
    }

    public Long getBizStorage() {
        return bizStorage;
    }

    public Long getBizOrder() {
        return bizOrder;
    }

    @PostConstruct
    private void initProducer() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(
                1,
                new BasicThreadFactory
                        .Builder()
                        .namingPattern("com.kevin.demo.nacos.config.worker-" + server)
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
