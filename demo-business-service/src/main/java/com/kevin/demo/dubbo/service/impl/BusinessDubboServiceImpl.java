package com.kevin.demo.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.kevin.demo.dubbo.service.AccountDubboService;
import com.kevin.demo.dubbo.service.BusinessDubboService;
import com.kevin.demo.dubbo.service.OrderDubboService;
import com.kevin.demo.dubbo.service.StorageDubboService;
import com.kevin.demo.entity.Account;
import com.kevin.demo.entity.Order;
import com.kevin.demo.entity.Storage;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2020/12/8 17:20
 */
@Service(group = "business_group", version = "1.0.0", protocol = "${dubbo.protocol.id}",
        application = "${dubbo.application.id}", registry = "${dubbo.registry.id}",
        timeout = 3000)
public class BusinessDubboServiceImpl implements BusinessDubboService {

    @Reference(group = "account_group", version = "1.0.0", retries = 0)
    private AccountDubboService accountService;

    @Reference(group = "order_group", version = "1.0.0", retries = 0)
    private OrderDubboService orderService;

    @Reference(group = "storage_group", version = "1.0.0", retries = 0)
    private StorageDubboService storageService;

    @Override
//    @GlobalTransactional(timeoutMills = 300000, name = "kevin-demo-group", rollbackFor = Exception.class)
    public void buy() {
        Account account = new Account();
        account.setAmount(1L);
        account.setUserId(1L);
        accountService.add(account);

        Order order = new Order();
        order.setAmount(1L);
        order.setUserId(1L);
        orderService.add(order);

        Storage storage = new Storage();
        storage.setAmount(1L);
        storage.setUserId(1L);
        storageService.add(storage);
    }
}
