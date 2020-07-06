package com.xyz.assignment.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Enabled exception in case of No Matching handler is found.
 */
@Service
public class DispatcherConfigurer implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DispatcherServlet) {
            ((DispatcherServlet) bean).setThrowExceptionIfNoHandlerFound(true);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
