package com.example.springbootweb.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

/**
 * 数据源的JDBC配置类
 */
@Configuration


public class JdbcConfig {


//    @Autowired
//    private JdbcProperties jdbcProperties;

    /**
     * 实例化Druid
     */
    @Bean
    @ConfigurationProperties(prefix = "jdbc")     //这个注解只能去读取 application.properties的文件 不能读取自定义的配置文件
    public DataSource getDataSource(){
        DruidDataSource source = new DruidDataSource();
        return source;
    }
}
