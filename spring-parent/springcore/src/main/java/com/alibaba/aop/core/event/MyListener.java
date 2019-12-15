package com.alibaba.aop.core.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.util.ClassUtils;

/**
 * @author sier.pys 2019/11/16
 */
//@Component
public class MyListener implements SmartApplicationListener {


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        MyEvent myEvent = (MyEvent) event;
        System.out.println(myEvent.getMessage() + "-------*********_________***********");
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {

        if (ClassUtils.isAssignable(MyEvent.class, eventType)) {
            return true;
        }
//
//        if (MyEvent.class.isAssignableFrom(eventType)) {
//            return true;
//        }
        return false;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        if (TypeEvent.class.isAssignableFrom(sourceType)) {
            return true;
        }
        return false;
    }
}
