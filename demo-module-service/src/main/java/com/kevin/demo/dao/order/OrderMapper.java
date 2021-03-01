package com.kevin.demo.dao.order;


import com.kevin.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrderMapper {
    List<Order> query();

    void insertSelective(Order order);
}