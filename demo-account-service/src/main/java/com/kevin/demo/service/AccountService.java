package com.kevin.demo.service;



import com.kevin.demo.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> query();

    void add(Account account);
}
