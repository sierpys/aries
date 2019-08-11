package com.alibaba.aop.core;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class Worker {

    @Cacheable("TASK")
    public String longTask(final long id) {
        System.out.printf("Running long task for id: %d...%n", id);
        return "Long task for id " + id + " is done";
    }

    public String shortTask(final long id) {
        System.out.printf("Running short task for id: %d...%n", id);
        return "Short task for id " + id + " is done";
    }

}