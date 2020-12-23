package com.kevin.demo.common.data.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2019/5/20 15:14
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("current thread [{}] datasource is {}", Thread.currentThread().getName(), DataSourceTypeManager.get());

        return DataSourceTypeManager.get();
    }
}
