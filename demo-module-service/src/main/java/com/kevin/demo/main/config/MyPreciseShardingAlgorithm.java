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
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {

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

    private String getRouter(PreciseShardingValue<String> shardingValue) throws NacosException {
        String router = null;

        NacosConfigBean nacosConfigBean = SpringContextHolder.getApplicationContext().getBean(NacosConfigBean.class);

        Map<String, List<String>> configList = analyzeConfig(nacosConfigBean);

        String caseKey = shardingValue.getValue();

        Iterator<Map.Entry<String, List<String>>> entries = configList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<String>> entry = entries.next();
            if (entry.getValue().contains(caseKey)) {
                router = entry.getKey();
                break;
            }
        }
        return router;
    }

    private Map<String, List<String>> analyzeConfig(NacosConfigBean nacosConfigBean) throws NacosException {
        String dbConfig = nacosConfigBean.getDBConfig();
        dbConfig = dbConfig.replaceAll("\\{", "").replaceAll("\\}", "");
        String[] dsArrays = dbConfig.split(",");

        Map<String, List<String>> configList = new HashMap<>();
        for (String oneConfig : dsArrays) {
            String[] oneConfigArrays = oneConfig.split(":");
            configList.put(oneConfigArrays[0], Arrays.asList(oneConfigArrays[1].split("\\|")));
        }

        return configList;
    }
}
