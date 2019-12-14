package com.Desert.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.Desert")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class WebMVC implements WebMvcConfigurer {

    @Value("${jdbc.driverClass}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Value("${pool.initialSize}")
    private int initialPoolSize;
    @Value("${pool.minSize}")
    private int minPoolSize;
    @Value("${pool.maxSize}")
    private int maxPoolSize;
    @Value("${pool.maxIdleTime}")
    private int maxIdleTime;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        dataSource.setInitialPoolSize(initialPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMaxIdleTime(maxIdleTime);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", hibernateDialect);

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.Desert.Entity");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
