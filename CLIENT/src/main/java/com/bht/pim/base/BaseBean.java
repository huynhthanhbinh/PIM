package com.bht.pim.base;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

/**
 *
 * @author bht
 */
public interface BaseBean {

    /**
     * such as constructor of spring bean
     * it works on both scopes SINGLETON & PROTOTYPE !
     */
    @PostConstruct
    default void initialize() {
        Logger.getLogger(BaseBean.class).info("[SPRING] BeanCreation: " + getClass().getSimpleName());
    }

    /**
     * such as destructor of spring bean
     * it works only with SINGLETON scope !
     *
     * to make this method execute,
     * must call ApplicationContext.close method !
     * one way to do that is call
     * applicationContext.registerShutdownHook();
     * see this line of code implemented in PimWorkbench.class
     * AnnotationConfigApplicationContext is injected by SpringBoot !
     */
    @PreDestroy
    default void destroy() {
        Logger.getLogger(BaseBean.class).info("[SPRING] BeanDestruction: " + getClass().getSimpleName());
    }
}
