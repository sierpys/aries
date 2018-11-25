package com.alibaba.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author sier.pys 11/3/18
 */
public class TestBean implements ApplicationListener<ApplicationEvent> {

    private volatile int count;
    private String name;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
