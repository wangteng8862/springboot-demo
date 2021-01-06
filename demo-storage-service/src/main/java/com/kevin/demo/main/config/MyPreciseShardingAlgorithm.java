package com.kevin.demo.main.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.geekplus.optimus.common.entity.exception.DBHandlerException;
import com.geekplus.optimus.common.util.string.StringUtil;
import com.kevin.demo.common.utils.SpringContextHolder;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.*;

/**
 * @Description: 单键分片
 * @Author: god_ole
 * @Date: 2020/12/30 19:30
 */
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {

        String router = null;
        try {
            router = this.getRouter(shardingValue);
            System.out.println(router);
        } catch (NacosException e) {
            e.printStackTrace();
        }

        if (StringUtil.isEmpty(router)) {
            throw new DBHandlerException("When get db router found exception, throught shardingValue not found router");
        }

        String result = null;
        for (String tableName : availableTargetNames) {
            if (tableName.equalsIgnoreCase(router)) {
                result = tableName;
            }
        }
        return result;
    }

    private String getRouter(PreciseShardingValue<Long> shardingValue) throws NacosException {
        String router = null;

        NacosConfigBean nacosConfigBean = SpringContextHolder.getApplicationContext().getBean(NacosConfigBean.class);

        Map<String, List<String>> configList = analyzeConfig(nacosConfigBean);

        String caseKey = shardingValue.getValue().toString();

        Iterator<Map.Entry<String, List<String>>> entries = configList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<String>> entry = entries.next();
            if (entry.getValue().contains(caseKey.substring(caseKey.length() - 1, caseKey.length()))) {
                router = entry.getKey();
                break;
            }
        }
        return router;
    }

    private Map<String, List<String>> analyzeConfig(NacosConfigBean nacosConfigBean) throws NacosException {
        String dbConfig = nacosConfigBean.getDBConfig();
        String[] dsArrays = dbConfig.split(",");

        Map<String, List<String>> configList = new HashMap<>();
        for (String ds : dsArrays) {
            String[] oneConfig = ds.substring(1, ds.length() - 1).split(":");
            configList.put(oneConfig[0], Arrays.asList(oneConfig[1].split("|")));
        }

        return configList;
    }
}
