package com.alibaba.aop.core.event;

import org.springframework.context.ApplicationListener;

/**
 * @author sier.pys 2019/11/16
 */
public class MyListener_ implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("MyListener _ receive message:" + event.getMessage());
    }
}
