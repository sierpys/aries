package com.alibaba.aop.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sier.pys 10/27/18
 */
public class XmlMain {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//
//        AutoWire autoWire = context.getBean("autoWire", AutoWire.class);
//        autoWire.print();

        AutoWire autoWire = new AutoWireImpl();
        autoWire.print();
//
//        Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld2");
//        logger.debug("Hello world.");
//
//        // print internal state
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        StatusPrinter.print(lc);
//        LoggerContext loggerContext = new LoggerContext();
//        JoranConfigurator joranConfigurator = new JoranConfigurator();
//        joranConfigurator.setContext(loggerContext);
//        new ContextInitializer(loggerContext).autoConfig();
//
//        Logger logger = loggerContext.getLogger(XmlMain.class);
//
//        logger.error("=============");
    }
}
