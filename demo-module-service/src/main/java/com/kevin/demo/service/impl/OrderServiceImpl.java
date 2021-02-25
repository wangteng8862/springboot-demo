package com.kevin.demo.service.impl;

import com.kevin.demo.dao.OrderMapper;
import com.kevin.demo.entity.Order;
import com.kevin.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2020/12/8 17:20
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper OrderMapper;

    @Override
    public List<Order> query() {
        return OrderMapper.query();
    }

    @Transactional
    @Override
    public void add(Order order) {
        OrderMapper.insertSelective(order);
    }


}
