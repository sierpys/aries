package com.alibaba.core.concurrent.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
@Configuration
@EnableAsync
public class Config {

    @Bean
    public Simple simple() {
        return new Simple();
    }

    @Bean(name = "taskExecutor")
    public ExecutorService threadPoolExecutor() {
        return Executors.newFixedThreadPool(3, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, ".sa.f.asdfa");
            }
        });
    }
}
