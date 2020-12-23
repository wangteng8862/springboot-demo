package com.kevin.demo.dubbo.service;


import com.kevin.demo.entity.Order;

import java.util.List;

public interface OrderDubboService {
    List<Order> query();

    void add(Order order);
}
