package com.alibaba.aop.core;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author sier.pys 10/19/18
 */
@Component
public class RailwayStation implements TicketService, InitializingBean {
    private String name;

    @Override
    public void sellTicket() {
        System.out.println("��Ʊ............��........" + name);
    }

    @Override
    public void inquire() {
        System.out.println("��ѯ.............");
    }

    @Override
    public void withdraw() {
        System.out.println("��Ʊ.............");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init() {
        System.out.println(name + "..............name");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name + "...........properties");
    }
}
