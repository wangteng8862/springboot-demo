package com.kevin.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order extends BaseEntity implements Serializable {
    private Long id;
    private Long userId;
    private Long amount;
}
