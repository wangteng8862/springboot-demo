package com.kevin.demo.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kevin.demo.dubbo.service.ModuleDubboService;
import com.kevin.demo.entity.Order;
import com.kevin.demo.entity.Storage;
import com.kevin.demo.service.OrderService;
import com.kevin.demo.service.StorageService;
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

    @Override
    public void buySomeone(Long warehouseId, Boolean flag) {

        Long userId = (long) (1 + Math.random() * (100));

        Order order = new Order();
        order.setAmount(10L);
        order.setUserId(userId);

        orderService.addNewTransaction(order);

        if (flag) {
            int result = 1 / 0;
        }

        Storage storage = new Storage();
        storage.setAmount(10L);
        storage.setUserId(userId);

        storageService.add(storage);
    }

}
