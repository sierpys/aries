package com.alibaba.aop.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sier.pys 10/27/18
 */
public class XmlMain {

    final static Logger logger = LoggerFactory.getLogger(XmlMain.class);

    public static void main(String[] args) {
        logger.info("AAAAAA {}", "Alibaba");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        AutoWire autoWire = applicationContext.getBean("autoWire", AutoWire.class);
        autoWire.print();
    }
}
