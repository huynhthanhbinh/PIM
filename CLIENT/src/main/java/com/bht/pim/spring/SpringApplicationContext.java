package com.bht.pim.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.bht.pim.base.BaseBean;

/**
 *
 * @author bht
 */
@Component
public final class SpringApplicationContext implements BaseBean, ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * get bean created before by app static context
     * @param <T> any object has been injected before
     * @return Bean of a specific class
     */
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    /**
     * used by Spring !!!!
     * please don't use it
     */
    @Override
    public synchronized void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        context = applicationContext;
    }
}