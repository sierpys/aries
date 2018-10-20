package com.alibaba.aop.core;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @author sier.pys 10/19/18
 */
public class TicketServiceThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception ex) {
        System.out.println("AFTER_THROWING....");
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println("调用过程出错啦！！！！！");
    }

}
