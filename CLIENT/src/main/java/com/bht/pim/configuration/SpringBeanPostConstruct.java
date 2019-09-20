package com.bht.pim.configuration;

import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;

import lombok.extern.log4j.Log4j;

/**
 * such as @PostConstruct using on each single bean, but this is common using for every beans
 *
 * @author bht
 */
@Log4j
@Component
public final class SpringBeanPostConstruct implements BaseBean, InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {

        if (beanClass.getPackage().getName().startsWith("com.bht.pim")) {
            log.info("[SPRING] BeanCreation: " + beanName);
        }
        return null;
    }
}