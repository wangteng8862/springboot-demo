package com.kevin.demo.service.impl;

import com.kevin.demo.dao.storage.StorageMapper;
import com.kevin.demo.entity.Storage;
import com.kevin.demo.service.StorageService;
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
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public List<Storage> query() {
        return storageMapper.query();
    }

    @Override
    public void add(Storage storage) {
        storageMapper.insertSelective(storage);
    }


}
