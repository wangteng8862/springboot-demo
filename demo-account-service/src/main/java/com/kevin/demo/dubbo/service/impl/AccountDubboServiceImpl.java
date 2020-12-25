package com.kevin.demo.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kevin.demo.dubbo.service.AccountDubboService;
import com.kevin.demo.entity.Account;
import com.kevin.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2020/12/8 17:20
 */
@Service(group = "account_group", version = "1.0.0", protocol = "${dubbo.protocol.id}",
        application = "${dubbo.application.id}", registry = "${dubbo.registry.id}",
        timeout = 3000)
public class AccountDubboServiceImpl implements AccountDubboService {
    private static final Logger logger = LoggerFactory.getLogger(AccountDubboServiceImpl.class);

    @Autowired
    private AccountService accountService;

    @Override
    public List<Account> query() {
        return accountService.query();
    }

    @Transactional
    @Override
    public void add(Account account) {
//        String xid = RootContext.getXID();
//        logger.info("【account service】accpet xid is {}", xid);
        accountService.add(account);
    }
}
