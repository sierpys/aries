package com.alibaba.spring.impl;

import com.alibaba.spring.S2;
import com.alibaba.spring.Service;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author sier.pys 9/15/18
 */
public class S2Impl implements S2, ApplicationContextAware {
    @Autowired
    private Service service;
//    @Autowired
//    private TaskExecutor taskExecutor;

    private ApplicationContext applicationContext;

    @Override
    public String find() {
//        for (int i = 0; i < 25; i++) {
//            final int j = i;
//            taskExecutor.execute(() -> {
//                System.out.println("message_" + j + ">>>" + Thread.currentThread().getName());
//            });
//
//        }
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
