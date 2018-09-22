package com.alibaba.core.spring.test;

import org.junit.ClassRule;
import org.junit.rules.ExternalResource;

/**
 * @author sier.pys 9/22/18
 */

//@ClassRule
public class EmbeddedKafkaRule extends ExternalResource {


    public EmbeddedKafkaRule() {
    }

    @Override
    protected void before() throws Throwable {
    }

    @Override
    protected void after() {
    }
}
