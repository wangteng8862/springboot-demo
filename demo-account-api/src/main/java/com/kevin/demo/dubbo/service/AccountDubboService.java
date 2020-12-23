package com.kevin.demo.dubbo.service;


import com.kevin.demo.entity.Account;

import java.util.List;

public interface AccountDubboService {
    List<Account> query();

    void add(Account account);
}
