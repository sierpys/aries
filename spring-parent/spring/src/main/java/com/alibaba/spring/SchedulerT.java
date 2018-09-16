package com.alibaba.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
@Configuration
public class SchedulerT {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "ThreadPoolTaskScheduler");
        threadPoolTaskScheduler.setErrorHandler(t -> System.out.println("出错了" + t.getMessage()));
        return threadPoolTaskScheduler;
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SchedulerT.class);
        ThreadPoolTaskScheduler taskScheduler = ctx.getBean(ThreadPoolTaskScheduler.class);
        Runnable runnable = () -> {
            if (atomicInteger.getAndIncrement() == 10) {
                throw new RuntimeException("jlfjasdj");
            }
            System.out.println("111" + Thread.currentThread().getName());
            System.out.println(atomicInteger.get() + ">>>>>>>>");
        };
        taskScheduler.scheduleAtFixedRate(runnable, new Date(System.currentTimeMillis() + 3000), 1000);

    }
}
