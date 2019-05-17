package com.sha.microservicecoursemanagement.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean icemfb = new LocalContainerEntityManagerFactoryBean();
        icemfb.setJpaVendorAdapter(getJpaVendorAdapter());
        icemfb.setDataSource(dataSource());
        icemfb.setPersistenceUnitName("entityManagerFactory");
        icemfb.setPackagesToScan("com.sha.microservicecoursemanagement.model","com.sha.coursemanagement.model");
        icemfb.setJpaProperties(hibernateProperties());
        return icemfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter(){
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return adapter;
    }

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    private final Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.max_fetch_depth", env.getProperty("hibernate.max_fetch_depth"));
        properties.put("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
        properties.put("hibernate.cache.use_minimal_puts", env.getProperty("hibernate.cache.use_minimal_puts"));
        properties.put("hibernate.connection_release_mode", env.getProperty("hibernate.connection_release_mode"));
        properties.put("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
        return properties;
    }
}
