package com.kevin.demo.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kevin.demo.dubbo.service.StorageDubboService;
import com.kevin.demo.entity.Storage;
import com.kevin.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2020/12/8 17:20
 */
@Service(group = "storage_group", version = "1.0.0", protocol = "${dubbo.protocol.id}",
        application = "${dubbo.application.id}", registry = "${dubbo.registry.id}",
        timeout = 3000)
public class StorageDubboServiceImpl implements StorageDubboService {
    @Autowired
    private StorageService storageService;

    @Override
    public List<Storage> query() {
        return storageService.query();
    }

    @Transactional
    @Override
    public void add(Storage storage) {
        storageService.add(storage);
    }
}
