package com.alibaba.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import com.alibaba.core.A;

/**
 * @author sier.pys 2019-06-22
 */
public class ContextListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();

        A bean = applicationContext.getBean(A.class);

        bean.print();
    }
}
