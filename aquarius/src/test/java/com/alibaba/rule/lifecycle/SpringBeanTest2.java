package com.alibaba.rule.lifecycle;

import com.alibaba.lifecycle.SpringBean;
import com.alibaba.rule.EmbeddedKafkaRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.util.Assert.notNull;

/**
 * @author sier.pys 8/17/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringBeanTest2 {

    @Autowired
    SpringBean springBean;

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafkaRule = new EmbeddedKafkaRule();

    @BeforeClass
    static public void set() {
        System.out.println("before class set");
    }

    @Before
    public void setBefore() {
        System.out.println("set before");
    }

    @Test
    public void springBean() {
        notNull(springBean, "spring bean dependency is null");
    }

    @Test
    public void springBean2() {
        notNull(springBean, "spring bean dependency is null");
    }


    @Configuration
    public static class Config {
        @Bean
        public SpringBean springBean() {
            return new SpringBean();
        }
    }
}
