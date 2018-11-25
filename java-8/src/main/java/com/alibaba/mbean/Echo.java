package com.alibaba.mbean;

/**
 * @author sier.pys 11/6/18
 */
public class Echo implements EchoMBean {
    @Override
    public void print(Integer yourName) {
        System.out.println(String.format("Your name is %d", yourName));
    }
}
