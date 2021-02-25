package com.kevin.demo.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kevin.demo.dubbo.service.ModuleDubboService;
import com.kevin.demo.entity.Order;
import com.kevin.demo.entity.Storage;
import com.kevin.demo.main.config.NacosConfigBean;
import com.kevin.demo.service.OrderService;
import com.kevin.demo.service.StorageService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2021/1/7 11:36
 */
@Service(group = "module_group", version = "1.0.0", protocol = "${dubbo.protocol.id}",
        application = "${dubbo.application.id}", registry = "${dubbo.registry.id}",
        timeout = 3000)
public class ModuleDubboServiceImpl implements ModuleDubboService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private NacosConfigBean configBean;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void buySomeone(Long warehouseId, Boolean flag) {

        Long userId = (long) (1 + Math.random() * (100));

        Order order = new Order();
        order.setAmount(10L);
        order.setBizId(configBean.getBizOrder());
        order.setWarehouseId(warehouseId);
        order.setShardingKey(getShardingKey(warehouseId, configBean.getBizOrder()));
        order.setUserId(userId);

        orderService.addNewTransaction(order);

        if (flag) {
            int result = 1 / 0;
        }

        Storage storage = new Storage();
        storage.setAmount(10L);
        storage.setBizId(configBean.getBizStorage());
        storage.setWarehouseId(warehouseId);
        storage.setShardingKey(getShardingKey(warehouseId, configBean.getBizStorage()));
        storage.setUserId(userId);

        storageService.add(storage);
    }

    private String getShardingKey(Long warehouseId, Long bizOrder) {
        return warehouseId.toString().concat("_").concat(bizOrder.toString());
    }
}
