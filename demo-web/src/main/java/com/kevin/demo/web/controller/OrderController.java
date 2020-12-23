package com.kevin.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.geekplus.optimus.common.entity.APIModel;
import com.geekplus.optimus.common.util.CommonUtil;
import com.geekplus.optimus.common.util.i18n.Lang;
import com.kevin.demo.dubbo.service.OrderDubboService;
import com.kevin.demo.entity.Account;
import com.kevin.demo.entity.Order;
import com.kevin.demo.web.controller.annotation.AddUrl;
import com.kevin.demo.web.controller.annotation.ListUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2020/12/8 17:16
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Reference(group = "order_group", version = "1.0.0")
    private OrderDubboService orderService;

    @ListUrl
    public APIModel query(Account account) {
        APIModel model = new APIModel();
        try {
            List<Order> orderList = orderService.query();
            model.set(CommonUtil.RETURN_CODE_ZERO, CommonUtil.RETURN_MSG_SUCCESS, orderList);
        } catch (Exception e) {
            // 运行异常
            logger.error("", e);
            model.set(CommonUtil.RETURN_CODE_ONE, Lang.msg("error") + e.getMessage(), e);
        }
        return model;
    }

    @AddUrl
    public APIModel add(@RequestBody Order order) {
        APIModel model = new APIModel();
        try {
            orderService.add(order);
            model.set(CommonUtil.RETURN_CODE_ZERO, CommonUtil.RETURN_MSG_SUCCESS, null);
        } catch (Exception e) {
            // 运行异常
            logger.error("", e);
            model.set(CommonUtil.RETURN_CODE_ONE, Lang.msg("error") + e.getMessage(), e);
        }
        return model;
    }
}
