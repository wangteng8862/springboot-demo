package com.kevin.demo.web.controller.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * @Description:批量删除方法RequestMapping注解
 * @Author: god_ole
 * @Date: 2019/2/28 15:36
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/deleteBatch", method = RequestMethod.GET)
public @interface BatchDeleteUrl {
}
