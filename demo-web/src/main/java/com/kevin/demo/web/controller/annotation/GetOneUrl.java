package com.kevin.demo.web.controller.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * @Description:查询单条数据方法RequestMapping注解
 * @Author: god_ole
 * @Date: 2019/2/28 15:37
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(value = "/get", method = RequestMethod.GET)
public @interface GetOneUrl {
}
