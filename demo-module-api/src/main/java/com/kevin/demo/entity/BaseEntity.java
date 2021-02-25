package com.kevin.demo.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2021/1/7 10:49
 */
@Data
public class BaseEntity {
    private Long warehouseId;
    private Long bizId;
    private String shardingKey;
}
