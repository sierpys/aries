package com.alibaba.aop.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sier.pys 2019-06-14
 */
@Component
public class A {
    @Autowired
    private B b;

    public void print() {
        System.out.println("B^^^^^^^A");
    }
}
