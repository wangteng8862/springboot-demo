package com.kevin.demo.dubbo.service;


import com.kevin.demo.entity.Storage;

import java.util.List;

public interface StorageDubboService {
    List<Storage> query();

    void add(Storage storage);
}
