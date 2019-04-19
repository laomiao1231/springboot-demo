package com.m.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.persistence.EntityManager;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef="primaryEntityManagerFactory", // 配置连接工厂
        transactionManagerRef="primaryTransactionManager", // 配置事物管理器
        basePackages= {"com.m.repository_primary"}) //设置Repository所在位置
public class JpaPrimaryDataSourceConfig {
    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;
    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;
    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;
    @Primary
    @Bean(name = "primaryEntityManager")
    public EntityManager primaryEntityManager(EntityManagerFactoryBuilder builder) {
        return primaryEntityManagerFactory(builder).getObject().createEntityManager();
    }
    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                //设置entity所在位置
                .packages("com.m.entity_primary")
                .persistenceUnit("primaryPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }
    @Primary
    @Bean(name = "primaryJpaTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }
    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }
//
//    @Bean(name = "secondaryEntityManager")
//    public EntityManager secondaryEntityManager(EntityManagerFactoryBuilder builder) {
//        return secondaryEntityManagerFactory(builder).getObject().createEntityManager();
//    }
//
//    @Bean(name = "secondaryEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(secondaryDataSource)
//                //设置entity所在位置
//                .packages("com.m.entity_primary")
//                .persistenceUnit("secondaryPersistenceUnit")
//                .properties(getVendorProperties())
//                .build();
//    }
//
//    @Bean(name = "secondaryJpaTransactionManager")
//    public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(secondaryEntityManagerFactory(builder).getObject());
//    }
}
