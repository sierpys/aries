package com.alibaba.concurrent;

import com.alibaba.thread.PC2Thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sier.pys 8/13/18
 */
public class NamedThreadFactory implements ThreadFactory {
    final static AtomicInteger ids = new AtomicInteger(0);
    final String factoryPrefix;
    final boolean daemon;

    public NamedThreadFactory(String factoryPrefix, boolean daemon) {
        this.factoryPrefix = factoryPrefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new PC2Thread(this.factoryPrefix + ids.getAndIncrement(), r, this.daemon);
    }
}
