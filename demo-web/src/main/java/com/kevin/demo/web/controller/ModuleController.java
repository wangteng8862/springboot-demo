package com.kevin.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.geekplus.optimus.common.entity.APIModel;
import com.geekplus.optimus.common.util.CommonUtil;
import com.geekplus.optimus.common.util.i18n.Lang;
import com.kevin.demo.dubbo.service.ModuleDubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2020/12/8 17:16
 */

@RestController
@RequestMapping("/module")
public class ModuleController {
    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Reference(group = "module_group", version = "1.0.0", retries = 0)
    private ModuleDubboService moduleDubboService;

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public APIModel buy(Long warehouseId, Boolean flag) {
        APIModel model = new APIModel();
        try {
            moduleDubboService.buySomeone(warehouseId, flag);
            model.set(CommonUtil.RETURN_CODE_ZERO, CommonUtil.RETURN_MSG_SUCCESS, null);
        } catch (Exception e) {
            // 运行异常
            logger.error("", e);
            model.set(CommonUtil.RETURN_CODE_ONE, Lang.msg("error") + e.getMessage(), e);
        }
        return model;
    }
}
