package com.bht.pim.configuration;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.util.LoggingUtil;

import lombok.extern.log4j.Log4j;

/**
 * such as @PostConstruct using on each single bean,
 * but this is common using for every beans
 * lifecycle: run before @PostConstruct
 *
 * @author bht
 */
@Log4j
@Component
public final class SpringBeanPostConstruct implements BaseBean, BeanPostProcessor {

    @Override
    public void initialize() {
        log.info(LoggingUtil.format("SPRING", "BeanCreation", getClass().getSimpleName()));
    }

    @Override
    public void destroy() {
        log.info(LoggingUtil.format("SPRING", "BeanDestruction", getClass().getSimpleName()));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, @NonNull String beanName) {
        if (bean.getClass().getPackage().getName().startsWith("com.bht.pim")) {
            log.info(LoggingUtil.format("SPRING", "BeanCreation", beanName));
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}