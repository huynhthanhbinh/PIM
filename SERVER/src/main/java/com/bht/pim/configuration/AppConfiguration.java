package com.bht.pim.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.bht.pim")
@Import(SpringBeanRegistration.class)
@PropertySource("classpath:/pim.properties")
public class AppConfiguration {
}