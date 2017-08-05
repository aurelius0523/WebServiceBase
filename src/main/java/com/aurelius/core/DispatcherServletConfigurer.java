package com.aurelius.core;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.servlet.DispatcherServlet;
//TODO remove
public class DispatcherServletConfigurer implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		if (bean instanceof DispatcherServlet) {
            ((DispatcherServlet) bean).setThrowExceptionIfNoHandlerFound(true);
        }
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		return bean;
	}
}
