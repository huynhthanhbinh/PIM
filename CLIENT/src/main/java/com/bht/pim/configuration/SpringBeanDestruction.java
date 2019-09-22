package com.bht.pim.configuration;

import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;
import com.bht.pim.util.LoggingUtil;

import lombok.extern.log4j.Log4j;

/**
 * such as @PreDestroy using on each single bean,
 * but this is common using for every beans
 * lifecycle: run before @PreDestroy
 *
 * @author bht
 */
@Log4j
@Component
public final class SpringBeanDestruction implements BaseBean, DestructionAwareBeanPostProcessor {

    @Override
    public void initialize() {
        log.info(LoggingUtil.format("SPRING", "BeanCreation", "springBeanDestruction"));
    }

    @Override
    public void destroy() {
        log.info(LoggingUtil.format("SPRING", "BeanDestruction", "springBeanDestruction"));
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, @NonNull String beanName) {

        if (bean.getClass().getPackage().getName().startsWith("com.bht.pim")) {
            log.info(LoggingUtil.format("SPRING", "BeanDestruction", beanName));
        }
    }
}