//package com.alibaba.aop.core;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.CustomBeanPostProcessor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
///**
// * @author sier.pys 10/21/18
// */
//@Component
//public class CustomizeBeanPostProcessor implements CustomBeanPostProcessor {
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        if (bean instanceof TicketService) {
//            System.out.println("自己定义的 after");
//        }
//        return bean;
//    }
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        if (bean instanceof TicketService) {
//            System.out.println("自己定义的 before");
//        }
//        return bean;
//    }
//}
