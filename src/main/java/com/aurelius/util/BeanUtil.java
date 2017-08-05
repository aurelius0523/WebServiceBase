package com.aurelius.util;

import org.springframework.context.ApplicationContext;

import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public final class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext staticContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
    	ApplicationContext context = applicationContext;
        setStaticContext(context);
    }
    
    public static <T> T getBean(Class<T> beanClass) {
        return staticContext.getBean(beanClass);
    }
    
    private static void setStaticContext(ApplicationContext applicationContext) {
    	BeanUtil.staticContext = applicationContext;
    }
}
