package com.kevin.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Storage extends BaseEntity implements Serializable {
    private Long id;
    private Long userId;
    private Long amount;
}
