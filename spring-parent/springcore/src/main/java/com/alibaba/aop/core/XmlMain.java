package com.alibaba.aop.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sier.pys 10/27/18
 */
public class XmlMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application.xml");
        TicketService bean = classPathXmlApplicationContext.getBean(TicketService.class);
        bean.sellTicket();
    }
}
