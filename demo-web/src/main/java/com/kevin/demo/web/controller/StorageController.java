package com.kevin.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.geekplus.optimus.common.entity.APIModel;
import com.geekplus.optimus.common.util.CommonUtil;
import com.geekplus.optimus.common.util.i18n.Lang;
import com.kevin.demo.dubbo.service.StorageDubboService;
import com.kevin.demo.entity.Storage;
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
@RequestMapping("/storage")
public class StorageController {
    private static final Logger logger = LoggerFactory.getLogger(StorageController.class);

    @Reference(group = "storage_group", version = "1.0.0")
    private StorageDubboService storageService;

    @ListUrl
    public APIModel query(Storage storage) {
        APIModel model = new APIModel();
        try {
            List<Storage> storageList = storageService.query();
            model.set(CommonUtil.RETURN_CODE_ZERO, CommonUtil.RETURN_MSG_SUCCESS, storageList);
        } catch (Exception e) {
            // 运行异常
            logger.error("", e);
            model.set(CommonUtil.RETURN_CODE_ONE, Lang.msg("error") + e.getMessage(), e);
        }
        return model;
    }

    @AddUrl
    public APIModel add(@RequestBody Storage storage) {
        APIModel model = new APIModel();
        try {
            storageService.add(storage);
            model.set(CommonUtil.RETURN_CODE_ZERO, CommonUtil.RETURN_MSG_SUCCESS, null);
        } catch (Exception e) {
            // 运行异常
            logger.error("", e);
            model.set(CommonUtil.RETURN_CODE_ONE, Lang.msg("error") + e.getMessage(), e);
        }
        return model;
    }
}
