package com.kevin.demo.common.utils;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2019/3/15 11:14
 */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 实现对spring context 的管理
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextHolder.applicationContext = context;
    }

    /**
     * @Description: applicationContext
     * @Author: god_ole
     * @Date: 2019/5/20 19:21
     * @Param: []
     * @Return org.springframework.context.ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @Description: 通过name获取 Bean.
     * @Author: god_ole
     * @Date: 2019/5/20 19:21
     * @Param: [name]
     * @Return java.lang.Object
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * @Description: 通过class获取Bean.
     * @Author: god_ole
     * @Date: 2019/5/20 19:21
     * @Param: [clazz]
     * @Return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * @Description: 通过name, 以及Clazz返回指定的Bean
     * @Author: god_ole
     * @Date: 2019/5/20 19:21
     * @Param: [name, clazz]
     * @Return T
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * @Description: 注册bean
     * @Author: god_ole
     * @Date: 2019/3/15 11:17
     * @Param: [beanId, className]
     * @Return void
     */
    public static <T> void registerBean(String beanId, Class<T> clz, ConfigurableApplicationContext applicationContext) {
        BeanDefinitionRegistry beanDefinitionRegistry = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

        // get the BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clz.getName());
        // get the BeanDefinition
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // register the bean
        beanDefinitionRegistry.registerBeanDefinition(beanId, beanDefinition);
    }
}