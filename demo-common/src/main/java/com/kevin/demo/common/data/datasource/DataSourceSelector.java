package com.kevin.demo.common.data.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2019/5/20 15:15
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceSelector {

    DataSourceTypeEnum value() default DataSourceTypeEnum.MASTER;
}
