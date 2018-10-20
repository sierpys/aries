package com.alibaba.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.io.IOException;

/**
 * @author sier.pys 8/17/18
 */
public class SpringBean implements BeanNameAware,BeanClassLoaderAware,BeanFactoryAware,ResourceLoaderAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {
    final Logger logger = LoggerFactory.getLogger(getClass());
    final String beanName;

    public SpringBean(String beanName) {
        logger.info("constructor beanname");
        this.beanName = beanName;
    }

    public SpringBean() {
        logger.info("constructor without beanname");
        this.beanName = SpringBean.class.getSimpleName();
    }

    @PostConstruct
    public void init() {
        logger.info("init method");
    }

    @Override
    public void setBeanName(String s) {
        logger.info("set bean name");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("destroy bean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("properties set after {}", this.beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("applicationContext ");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("post before");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("post after");
        return null;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {

        System.out.println("====" + resourceLoader.getClass().getName());

    }

    @Scheduled(fixedRate = 2)
    public void doSome() {
        System.out.println(">>>>>>>>>");
    }
}
