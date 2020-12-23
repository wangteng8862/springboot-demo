package com.kevin.demo.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kevin.demo.dubbo.service.OrderDubboService;
import com.kevin.demo.entity.Order;
import com.kevin.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2020/12/8 17:20
 */
@Service(group = "order_group", version = "1.0.0", protocol = "${dubbo.protocol.id}",
        application = "${dubbo.application.id}", registry = "${dubbo.registry.id}",
        timeout = 3000)
public class OrderDubboServiceImpl implements OrderDubboService {
    @Autowired
    private OrderService accountService;

    @Override
    public List<Order> query() {
        return accountService.query();
    }

    @Transactional
    @Override
    public void add(Order order) {
        accountService.add(order);

//        int i = 1/0;
    }
}
