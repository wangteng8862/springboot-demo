package com.kevin.demo.common.data.datasource.aspect;

import com.kevin.demo.common.data.datasource.DataSourceSelector;
import com.kevin.demo.common.data.datasource.DataSourceTypeEnum;
import com.kevin.demo.common.data.datasource.DataSourceTypeManager;
import com.kevin.demo.common.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2019/5/20 15:24
 */
@Slf4j
public class DetermineDataSourceAspect implements Ordered {

    // 默认拦截的方法
    private List<String> useSlaveDataSourceMethodPrefixes = Arrays.asList("list", "query", "get", "select", "page");

    /**
     * 环绕增强方法
     *
     * @param joinPoint 连接点对象
     * @return 执行结果
     * @throws Throwable 执行环绕增强方法异常
     */
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //确定方法所用的数据源
        determineDataSource(joinPoint);

        try {
            // 调用方法
            Object res = joinPoint.proceed();
            return res;
        } finally {
            // 执行完毕后再切回主库
            if (DataSourceTypeManager.get() != DataSourceTypeEnum.MASTER) {
                log.info("the method {} finished and now switching datasource from {} to {} ", joinPoint.getSignature().getName(), DataSourceTypeManager.get(), DataSourceTypeEnum.MASTER);
                DataSourceTypeManager.set(DataSourceTypeEnum.MASTER);
            }
        }
    }


    /**
     * 确定数据源（是主库还是从库数据源）
     *
     * @param joinPoint 连接点对象
     * @throws NoSuchMethodException 被拦截的方法不存在异常
     */
    private void determineDataSource(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        DataSourceTypeEnum dataSourceType = DataSourceTypeEnum.MASTER;

        // 从被拦截的方法上获取数据源选择器注解
        DataSourceSelector dataSourceSelector = getDataSourceSelector(joinPoint);

        // 获得执行方法名
        String methodName = joinPoint.getSignature().getName();

        if (dataSourceSelector != null) {
            dataSourceType = dataSourceSelector.value();
        } else {
            // 从上下文获取动态数据源是否存在i18n
            String targetClassStr = joinPoint.getTarget().getClass().toString();
            targetClassStr = targetClassStr.substring(targetClassStr.lastIndexOf(".") + 1, targetClassStr.length());
            if (targetClassStr.equals("VenusI18nServiceImpl") && SpringContextHolder.getBean("i18n") != null) {
                dataSourceType = DataSourceTypeEnum.I18N;
            } else {
                // 如果方法需要走从库，则切换当前线程的数据源到从库
                boolean useSlave = useSlaveDataSourceMethodPrefixes.stream().anyMatch(i -> methodName.startsWith(i));
                if (useSlave) {
                    dataSourceType = DataSourceTypeEnum.SLAVE;
                }
            }
        }

        if (dataSourceType == DataSourceTypeEnum.SLAVE) {
            DataSourceTypeManager.set(DataSourceTypeEnum.SLAVE);
        }
        if (dataSourceType == DataSourceTypeEnum.I18N) {
            DataSourceTypeManager.set(DataSourceTypeEnum.I18N);
        }

        log.info("the method {} datasource is {}", methodName, DataSourceTypeManager.get());
    }

    /**
     * 从被拦截的方法上获取数据源选择器注解
     *
     * @param joinPoint 连接点对象
     * @return 数据源选择器注解
     * @throws NoSuchMethodException 被拦截的方法不存在异常
     */
    private DataSourceSelector getDataSourceSelector(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Class<?> targetClass = joinPoint.getTarget().getClass();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Class<?>[] parameterTypes = methodSignature.getParameterTypes();

        String methodName = joinPoint.getSignature().getName();
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);

        return objMethod.getAnnotation(DataSourceSelector.class);
    }

    /**
     * 设置使用从库数据源的方法前缀名集合
     *
     * @param useSlaveDataSourceMethodPrefixes 使用从库数据源的方法前缀名集合
     */
    public void setUseSlaveDataSourceMethodPrefixes(List<String> useSlaveDataSourceMethodPrefixes) {
        this.useSlaveDataSourceMethodPrefixes = useSlaveDataSourceMethodPrefixes;
    }

    /* (non-Javadoc)
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1; // 优先级要高高高高高
    }
}
