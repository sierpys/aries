package com.alibaba.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextLoader;

/**
 * @author sier.pys 8/31/18
 */
public class SpringContextLoader implements ContextLoader {
    @Override
    public String[] processLocations(Class<?> clazz, String... locations) {
        return new String[0];
    }

    @Override
    public ApplicationContext loadContext(String... locations) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:*.xml");
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext(context);


        return null;
    }
}
