package com.alibaba.core.concurrent.executor;

import com.alibaba.core.concurrent.scheduling.support.TaskUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ExecutorService;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class Simple {


    @Async
    public void doSome() {
        System.out.println("do some " + Thread.currentThread().getName());
    }


//
//
//    @Bean
//    public ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean() {
//        ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean = new ThreadPoolExecutorFactoryBean();
//        threadPoolExecutorFactoryBean.setCorePoolSize(10);
//        threadPoolExecutorFactoryBean.setQueueCapacity(10);
//        return threadPoolExecutorFactoryBean;
//    }

//    @Bean
//    public ExecutorService executorService() {
//        return threadPoolExecutorFactoryBean().getObject();
//    }


//    public static void main(String[] args) {
////        SyncTaskExecutor syncTaskExecutor = new SyncTaskExecutor();
////        syncTaskExecutor.execute(() -> System.out.println("......" + Thread.currentThread().getName()));
//
//
////        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
////
////        TaskDecorator taskDecorator = runnable -> TaskUtils.decorateTaskWithErrorHandler(runnable, null, true);
////
////
////        simpleAsyncTaskExecutor.setTaskDecorator(taskDecorator);
////
////        simpleAsyncTaskExecutor.execute(()->{
////            throw new RuntimeException("rum time");
////        });
////
////        try {
////            Thread.sleep(100000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////    }
//
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Simple.class);
////        ExecutorService executorService = context.getBean(ExecutorService.class);
//        .submit(() -> {
//            System.out.println("...." + Thread.currentThread().getName());
//        });
//    }


}
