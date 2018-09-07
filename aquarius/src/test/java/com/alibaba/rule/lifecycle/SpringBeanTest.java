package com.alibaba.rule.lifecycle;

import com.alibaba.lifecycle.Config;
import com.alibaba.lifecycle.SpringBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.util.Assert.notNull;

/**
 * @author sier.pys 8/17/18
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Config.class})
public class SpringBeanTest {


    @Autowired
    SpringBean springBean;
    @Autowired

    @Test
    public void springBean() {
        notNull(springBean, "spring bean dependency is null");
    }


}
