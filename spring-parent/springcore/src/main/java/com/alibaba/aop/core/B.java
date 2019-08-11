package com.alibaba.aop.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sier.pys 2019-06-14
 */
@Component
public class B {
    @Autowired
    private A a;

    public void print() {
        System.out.println("A^^^^^B");
    }
}
