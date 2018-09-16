package com.alibaba.core.concurrent;

import com.alibaba.core.concurrent.executor.Simple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Simple.class})
public class SpringBean {
    @Autowired
    private ExecutorService executorService;

//    @Autowired
//    private ThreadPoolExecutorFactoryBean threadPoolExecutorFactoryBean;


    @Test
    public void testExe() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("...");
            }
        });

//        threadPoolExecutorFactoryBean.getObject().execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(".....");
//            }
//        });
    }
}

