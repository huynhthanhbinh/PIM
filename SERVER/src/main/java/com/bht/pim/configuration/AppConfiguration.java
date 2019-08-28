package com.bht.pim.configuration;

import java.util.Objects;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.mapstruct.factory.Mappers;
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

import com.bht.pim.mapper.CustomizedMapper;
import com.bht.pim.mapper.DateTimeMapper;
import com.bht.pim.mapper.EmployeeMapper;
import com.bht.pim.mapper.GroupMapper;
import com.bht.pim.mapper.ProjectMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Configuration
@EnableWebMvc
@SuppressWarnings("all")
@EnableTransactionManagement
@ComponentScan("com.bht.pim")
@PropertySource("classpath:/pim.properties")
public class AppConfiguration {

    // In Spring, we can use annotation @PropertySource
    // to externalize our configurations to
    // a properties file under src/main/resources folder
    // Therefore, we can get these values through
    // @Value annotation or Environment object(@Autowired)
    // For eg. @Value("${mssql.url}") private String url
    // Remind: using # for comment in properties file !
    @Autowired
    private Environment environment;


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
    // @PropertySource("classpath:pim.properties")
    @Bean
    public DataSource dataSource() {
        log.info("");
        log.info("<<< PIM SERVER - CONFIGURE DATA SOURCE >>>");
        log.info("");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects
                .requireNonNull(environment.getProperty("mssql.DriverClassName")));
        dataSource.setUrl(environment.getProperty("mssql.Url"));
        dataSource.setUsername(environment.getProperty("mssql.Username"));
        dataSource.setPassword(environment.getProperty("mssql.Password"));

        //  to enable Prepared Statement Handle Caching in the mssql jdbc driver
        Properties jdbcProperties = new Properties();
        jdbcProperties.put("disableStatementPooling", Objects
                .requireNonNull(environment.getProperty("mssql.disableStatementPooling")));
        jdbcProperties.put("statementPoolingCacheSize", Objects
                .requireNonNull(environment.getProperty("mssql.statementPoolingCacheSize")));
        dataSource.setConnectionProperties(jdbcProperties);

        return dataSource;
    }


    // Use to read messages properties for messages, logging ...
    @Bean
    public MessageSource messageSource() {
        log.info("");
        log.info("<<< PIM SERVER - CONFIGURE MESSAGE SOURCE >>>");
        log.info("");

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
        log.info("");
        log.info("<<< PIM SERVER - CONFIGURE SESSION FACTORY >>>");
        log.info("");

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
        hibernateProperties.put("hibernate.legacy_limit_handler",
                Objects.requireNonNull(environment.getProperty("hibernate.legacy_limit_handler"))); //pagination


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

        log.info("");
        log.info("<<< PIM SERVER - TRANSACTION MANAGER >>>");
        log.info("");

        HibernateTransactionManager hibernateTransactionManager =
                new HibernateTransactionManager();

        // Set session factory
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        hibernateTransactionManager.setRollbackOnCommitFailure(true);

        return hibernateTransactionManager;
    }


    @Bean
    public EmployeeMapper employeeMapper() {
        log.info("[PIM] Creating bean of < EmployeeMapper >");
        return Mappers.getMapper(EmployeeMapper.class);
    }

    @Bean
    public ProjectMapper projectMapper() {
        log.info("[PIM] Creating bean of < ProjectMapper >");
        return Mappers.getMapper(ProjectMapper.class);
    }

    @Bean
    public GroupMapper groupMapper() {
        log.info("[PIM] Creating bean of < GroupMapper >");
        return Mappers.getMapper(GroupMapper.class);
    }

    @Bean
    public CustomizedMapper customizedMapper() {
        log.info("[PIM] Creating bean of < CustomizedMapper >");
        return Mappers.getMapper(CustomizedMapper.class);
    }

    @Bean
    public DateTimeMapper dateTimeMapper() {
        log.info("[PIM] Creating bean of < DateTimeMapper >");
        return Mappers.getMapper(DateTimeMapper.class);
    }
}
