package com.alibaba.aop.core;

import org.springframework.stereotype.Component;

/**
 * @author sier.pys 11/5/18
 */
@Component
public class AutoWireImpl implements AutoWire {

    @Override
    public void print() {
        System.out.println("autowire");
    }
}
