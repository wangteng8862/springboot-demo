package com.kevin.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.geekplus.optimus.common.entity.APIModel;
import com.geekplus.optimus.common.util.CommonUtil;
import com.geekplus.optimus.common.util.i18n.Lang;
import com.kevin.demo.dubbo.service.BusinessDubboService;
import com.kevin.demo.entity.Storage;
import com.kevin.demo.web.controller.annotation.AddUrl;
import com.kevin.demo.web.controller.annotation.ListUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2020/12/8 17:16
 */

@RestController
@RequestMapping("/business")
public class BusinessController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    @Reference(group = "business_group", version = "1.0.0")
    private BusinessDubboService businessService;

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public APIModel query() {
        APIModel model = new APIModel();
        try {
            businessService.buy();
            model.set(CommonUtil.RETURN_CODE_ZERO, CommonUtil.RETURN_MSG_SUCCESS, null);
        } catch (Exception e) {
            // 运行异常
            logger.error("", e);
            model.set(CommonUtil.RETURN_CODE_ONE, Lang.msg("error") + e.getMessage(), e);
        }
        return model;
    }
}
