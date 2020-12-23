package com.kevin.demo.service;


import com.kevin.demo.entity.Storage;

import java.util.List;

public interface StorageService {

    List<Storage> query();

    void add(Storage storage);
}
