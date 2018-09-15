package com.alibaba.spring.impl;

import com.alibaba.spring.S2;
import com.alibaba.spring.Service;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author sier.pys 9/15/18
 */
public class S2Impl implements S2, ApplicationContextAware {
    private Service service;
    private ApplicationContext applicationContext;

    @Override
    public String find() {
        return service.find();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
