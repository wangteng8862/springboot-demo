package com.kevin.demo.dubbo.service;

import com.kevin.demo.entity.Order;
import com.kevin.demo.entity.Storage;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2021/1/7 11:34
 */
public interface ModuleDubboService {

    /**
     * @Description:
     * @Author: god_ole
     * @Date: 2021/1/7 11:36
     */
    void buySomeone(Long warehouseId, Boolean flag);
}
