package com.kevin.demo.service.impl;

import com.kevin.demo.dao.AccountMapper;
import com.kevin.demo.entity.Account;
import com.kevin.demo.service.AccountService;
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
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> query() {
        return accountMapper.query();
    }

    @Transactional
    @Override
    public void add(Account account) {
        accountMapper.insertSelective(account);
    }


}
