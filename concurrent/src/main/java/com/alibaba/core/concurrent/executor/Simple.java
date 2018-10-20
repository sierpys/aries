package com.alibaba.core.concurrent.executor;

import com.alibaba.core.concurrent.scheduling.support.TaskUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
@Component
public class Simple implements ApplicationContextAware {

    @Async
    public void doSome() {
        System.out.println("do some " + Thread.currentThread().getName());
    }

    @Scheduled(fixedRate = 2)
    public void doDo() {
        System.out.println("......");
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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
