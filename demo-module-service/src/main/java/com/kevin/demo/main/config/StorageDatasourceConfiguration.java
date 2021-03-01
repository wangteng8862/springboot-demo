package com.kevin.demo.main.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = {"com.kevin.demo.dao.storage"}, sqlSessionTemplateRef = "storageSqlSessionTemplate")
public class StorageDatasourceConfiguration {
    @Autowired
    private StorageDatasourceProperties storageDatasourceProperties;

    @Bean(name = "storageDatasource")
    public DataSource storageDruidDatasource() {
        DruidXADataSource datasource = new DruidXADataSource();
        BeanUtils.copyProperties(storageDatasourceProperties, datasource);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(datasource);
        xaDataSource.setUniqueResourceName("storageDatasource");
        //return DataSourceBuilder.create().build();
        return xaDataSource;
    }

    //配置数据源
    @Bean(name = "storageSqlSessionFactory")
    public SqlSessionFactory driverSqlSessionFactory(@Qualifier("storageDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        //mapper.xml 的位置
        Resource[] resources1 = pathMatchingResourcePatternResolver.getResources("classpath*:com/kevin/demo/dao/*.xml");
        factoryBean.setMapperLocations(resources1);
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean(name = "storageSqlSessionTemplate")
    public SqlSessionTemplate driverSqlSessionTemplate(@Qualifier("storageSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}