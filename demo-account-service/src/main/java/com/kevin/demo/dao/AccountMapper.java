package com.kevin.demo.dao;


import com.kevin.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<Account> query();

    void insertSelective(Account account);
}