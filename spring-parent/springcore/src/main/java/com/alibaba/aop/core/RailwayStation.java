package com.alibaba.aop.core;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author sier.pys 10/19/18
 */
@Component
public class RailwayStation implements TicketService {
    public RailwayStation() {
        System.out.println("初始化");
    }

    @Override
    public void sellTicket() {
        System.out.println("售票............");
    }

    @Override
    public void inquire() {
        System.out.println("问询.............");
    }

    @Override
    public void withdraw() {
        System.out.println("退票.............");
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        if (applicationContext instanceof ConfigurableBeanFactory) {
            ((ConfigurableBeanFactory) applicationContext).addBeanPostProcessor(new CustomizeBeanPostProcessor());
        }
        applicationContext.register(RailwayStation.class);
        applicationContext.register(CustomizeBeanPostProcessor.class);
        applicationContext.register(Bus.class);
        applicationContext.refresh();


//        TicketService bean = applicationContext.getBean(TicketService.class);
//        bean.sellTicket();
    }
}
