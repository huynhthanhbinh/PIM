package com.bht.pim.configuration;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.bht.pim")
@PropertySource("classpath:db.properties")
public class AppConfiguration {

    private Logger logger = Logger.getLogger(AppConfiguration.class);

    // In Spring, we can use annotation @PropertySource
    // to externalize our configurations to
    // a properties file under src/main/resources folder
    // Therefore, we can get these values through
    // @Value annotation or Environment object(@Autowired)
    // For eg. @Value("${mssql.url}") private String url
    // Remind: using # for comment in properties file !
    @Autowired
    Environment environment;


    // As Environment obj is in the same class
    // Configurer method need to be STATIC one
    // As environment object can access directly
    @Bean
    public static PropertySourcesPlaceholderConfigurer
    placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    // DataSource / Database Configuration
    // Using properties file in src/main/resources
    // @PropertySource("classpath:db.properties")
    @Bean
    public DataSource dataSource() {
        logger.info("");
        logger.info("<<< PIM SERVER - CONFIGURE DATA SOURCE >>>");
        logger.info("");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects
                .requireNonNull(environment.getProperty("mssql.DriverClassName")));
        dataSource.setUrl(environment.getProperty("mssql.Url"));
        dataSource.setUsername(environment.getProperty("mssql.Username"));
        dataSource.setPassword(environment.getProperty("mssql.Password"));

        return dataSource;
    }


    // Use to read messages properties for messages, logging ...
    @Bean
    public MessageSource messageSource() {
        logger.info("");
        logger.info("<<< PIM SERVER - CONFIGURE MESSAGE SOURCE >>>");
        logger.info("");

        ReloadableResourceBundleMessageSource bundleMessageSource =
                new ReloadableResourceBundleMessageSource();

        // current class path is: src/main/java/ -> go to messages.properties
        // since all properties config files are in classpath
        // need to define the path with prefix "classpath(*):"
        // otherwise, it'll look into the web directory: webapp
        bundleMessageSource.setBasename("classpath:messages");
        bundleMessageSource.setDefaultEncoding("UTF-8");

        return bundleMessageSource;
    }


    // Config Session Factory / Hibernate
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        logger.info("");
        logger.info("<<< PIM SERVER - CONFIGURE SESSION FACTORY >>>");
        logger.info("");

        // Session Factory Configure
        LocalSessionFactoryBean bean =
                new LocalSessionFactoryBean();


        // DataSource as learnt before:
        // Eg. MSSQL Config, DB: BHT, localhost
        bean.setDataSource(dataSource());


        // Package contains class mapping record to model
        // eg. com.bht.pim.entities
        bean.setPackagesToScan(environment.getProperty("packages.entities"));

        // Hibernate Properties for hibernate extra config
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect",
                Objects.requireNonNull(environment.getProperty("hibernate.dialect")));
        hibernateProperties.put("hibernate.show_sql",
                Objects.requireNonNull(environment.getProperty("hibernate.show_sql")));
        hibernateProperties.put("hibernate.connection.pool_size",
                Objects.requireNonNull(environment.getProperty("hibernate.connection.pool_size")));
        hibernateProperties.put("hibernate.connection.autocommit",
                Objects.requireNonNull(environment.getProperty("hibernate.connection.autocommit")));


        // Assign hibernateProperties to SessionFactory Config
        bean.setHibernateProperties(hibernateProperties);

        return bean;
    }


    // For handling transactions / connections
    // Spring support @EnableTransactionManagement
    // Transaction use in Service Class, DAO Class
    // not using for Controller !!! meaningless !!
    // 2 ways of using TX:
    // + Transaction for the whole class service/DAO
    // + Transaction for each unit method inside service/DAO
    // We can have multi different bean transaction manager
    // Therefore, we need to declare exactly the bean name
    /*@Bean("transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }*/
    @Bean("transactionManager")
    public HibernateTransactionManager hibernateTransactionManager(
            @Autowired SessionFactory sessionFactory) {

        logger.info("");
        logger.info("<<< PIM SERVER - TRANSACTION MANAGER >>>");
        logger.info("");

        HibernateTransactionManager hibernateTransactionManager =
                new HibernateTransactionManager();

        // Set session factory
        hibernateTransactionManager.setSessionFactory(sessionFactory);

        return hibernateTransactionManager;
    }
}
