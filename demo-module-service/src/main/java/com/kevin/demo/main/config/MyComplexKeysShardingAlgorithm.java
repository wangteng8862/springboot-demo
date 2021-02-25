package com.kevin.demo.main.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.geekplus.optimus.common.entity.exception.DBHandlerException;
import com.geekplus.optimus.common.util.string.StringUtil;
import com.kevin.demo.common.utils.SpringContextHolder;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.*;

/**
 * @Description: 复核分片
 * @Author: god_ole
 * @Date: 2021/1/4 9:52
 */
public class MyComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {

        return null;
    }
}
