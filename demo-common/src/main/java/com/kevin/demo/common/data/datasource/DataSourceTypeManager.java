package com.kevin.demo.common.data.datasource;

/**
 * @Description:
 * @Author: god_ole
 * @Date: 2019/5/20 15:14
 */
public class DataSourceTypeManager {
    private static final ThreadLocal<DataSourceTypeEnum> dataSourceTypes =
            new ThreadLocal<DataSourceTypeEnum>() {
                protected DataSourceTypeEnum initialValue() {
                    return DataSourceTypeEnum.MASTER; // 默认访问MASTER
                }
            };

    /**
     * 获得数据源类型
     *
     * @return 数据源类型（主库、从库）
     */
    public static DataSourceTypeEnum get() {
        return dataSourceTypes.get();
    }

    /**
     * 设置数据源类型
     *
     * @param dataSourceType 数据源类型（主库、从库）
     */
    public static void set(DataSourceTypeEnum dataSourceType) {
        dataSourceTypes.set(dataSourceType);
    }
}
