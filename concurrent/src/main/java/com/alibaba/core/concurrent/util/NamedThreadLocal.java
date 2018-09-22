package com.alibaba.core.concurrent.util;

import org.springframework.util.Assert;

/**
 * @author sier.pys 9/22/18
 */
public class NamedThreadLocal<T> extends ThreadLocal<T> {
    private final String name;

    public NamedThreadLocal(String name) {
        Assert.hasText(name, "ThreadLocal Name must not be null");
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

