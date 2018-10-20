package com.alibaba.core.concurrent.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
@Configuration
@EnableAsync
@EnableScheduling
public class Config implements SchedulingConfigurer {


    @Bean
    public Simple simple() {
        return new Simple();
    }

    //    @Bean(name = "taskExecutor")
//    public ExecutorService threadPoolExecutor() {
//        AtomicInteger atomicInteger = new AtomicInteger(0);
//        return Executors.newFixedThreadPool(3, r -> new Thread(r, "customizable_thread_" + atomicInteger.getAndIncrement()));
//    }
    public static void main(String[] args) throws IOException {
        ConcurrentReferenceHashMap<Object, Object> hashMap = new ConcurrentReferenceHashMap<>();
        hashMap.getOrDefault("aaaa", "aaaa");
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

}
