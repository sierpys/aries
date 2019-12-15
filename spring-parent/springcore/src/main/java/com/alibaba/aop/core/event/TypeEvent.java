package com.alibaba.aop.core.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author sier.pys 2019/11/16
 */
public class TypeEvent<T extends ApplicationEvent> {
    public static <T> T get(T t) {
        return t;
    }
}
