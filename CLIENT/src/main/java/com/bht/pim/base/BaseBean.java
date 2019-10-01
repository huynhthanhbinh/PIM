package com.bht.pim.base;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.bht.pim.spring.SpringBeanLifeCycle;

/**
 *
 * @author bht
 */
public interface BaseBean {

    /**
     * run after SpringBeanLifeCycle.postProcessBeforeInitialization()
     * @see SpringBeanLifeCycle
     *
     * such as constructor of spring bean
     * it works on both scopes SINGLETON & PROTOTYPE !
     *
     * if need to override it,
     * must call BaseBean.super.initialize(); first !!!!!
     */
    @PostConstruct
    default void initialize() throws IOException {
    }


    /**
     * run after SpringBeanLifeCycle.postProcessBeforeDestruction()
     * @see SpringBeanLifeCycle
     *
     * such as destructor of spring bean
     * it works only with SINGLETON scope !
     *
     * to make this method execute, must call ApplicationContext.close() method !
     * one way to do that is call
     * <code>applicationContext.registerShutdownHook();</code>
     * see this line of code implemented in PimWorkbench.class
     * AnnotationConfigApplicationContext is injected by SpringBoot !
     *
     * if need to override it
     * must call BaseBean.super.destroy(); first !!!!!
     */
    @PreDestroy
    default void destroy() {
    }
}