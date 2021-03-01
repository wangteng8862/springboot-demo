package com.kevin.demo.dao.storage;


import com.kevin.demo.entity.Storage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageMapper {
    List<Storage> query();

    void insertSelective(Storage storage);
}