package com.bht.pim.configuration;

import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;

import lombok.extern.log4j.Log4j;

/**
 * such as @PreDestroy using on each single bean, but this is common using for every beans
 *
 * @author bht
 */
@Log4j
@Component
public final class SpringBeanPreDestroy implements BaseBean, DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object o, @NonNull String s) {

        if (o.getClass().getPackage().getName().startsWith("com.bht.pim")) {
            log.info("[SPRING] BeanDestruction: " + s);
        }
    }
}