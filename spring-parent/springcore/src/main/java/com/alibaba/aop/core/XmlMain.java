package com.alibaba.aop.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sier.pys 10/27/18
 */
public class XmlMain {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfiguration.class);
//        Printer printer = applicationContext.getBean(Printer.class);
//        printer.print("hello world");

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        SourceDataProvider dataProvider = context.getBean(SourceDataProvider.class);
        dataProvider.printSource();
    }
}
