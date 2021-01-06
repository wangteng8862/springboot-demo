package com.kevin.demo.main.config;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

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
