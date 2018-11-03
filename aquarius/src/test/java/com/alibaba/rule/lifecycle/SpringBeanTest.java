package com.alibaba.rule.lifecycle;

import com.alibaba.lifecycle.Config;
import com.alibaba.lifecycle.Service;
import com.alibaba.lifecycle.SpringBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.util.Assert.notNull;

/**
 * @author sier.pys 8/17/18
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Config.class}, initializers = {ApplicationContextInitializerT.class})
public class SpringBeanTest {


    @Autowired
    SpringBean springBean;


    @Autowired
    private Service service;

    @Test
    public void springBean() {
        System.out.println(service.doSomething());
    }


}
