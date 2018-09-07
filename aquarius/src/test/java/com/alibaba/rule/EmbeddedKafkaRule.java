package com.alibaba.rule;

import org.junit.rules.ExternalResource;

/**
 * @author sier.pys 8/17/18
 */
public class EmbeddedKafkaRule extends ExternalResource {


    @Override
    protected void before() throws Throwable {
        System.out.println("class rule before");
    }

    @Override
    protected void after() {
        System.out.println("class rule after");
    }
}
