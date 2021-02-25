package com.kevin.demo.service;


import com.kevin.demo.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> query();

    void add(Order order);
}
