package com.gissolution.webapi.configuration;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EntityScan(basePackages = "com.gissolution.webdata.entity.*")
@EnableJpaRepositories(basePackages= "com.gissolution.webdata.dao")
@ComponentScan(basePackages = "com.gissolution.webdata.*")
public class PersistenceConfiguration {

    @Bean
    DataSource dataSource(Environment env) {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("spring.datasource.driverClassName"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("spring.datasource.password"));

        return new HikariDataSource(dataSourceConfig);
    }





    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.gissolution.webdata.entity");

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("spring.hibernate.dialect"));
        if(env.getRequiredProperty("spring.hibernate.hbm2ddl.auto").equals("create")){
            if(!env.getRequiredProperty("env").equals("test")){
                throw new RuntimeException("I will Not allow This. We will loose Data Man, ese env=test for test env");
            }
        }
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
        setOptionalProperty(env, jpaProperties, "spring.hibernate.scanner","hibernate.ejb.resource_scanner");
        setOptionalProperty(env, jpaProperties, "spring.hibernate.format_sql","hibernate.format_sql");
        setOptionalProperty(env, jpaProperties, "spring.hibernate.show_sql","hibernate.show_sql");

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    private void setOptionalProperty(Environment env, Properties jpaProperties, String springProperty, String hibernateProperty) {
        if(env.containsProperty(springProperty)) {
            jpaProperties.put(hibernateProperty, env.getRequiredProperty(springProperty));
        }
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
