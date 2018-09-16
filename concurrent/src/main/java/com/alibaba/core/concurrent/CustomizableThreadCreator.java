package com.alibaba.core.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class CustomizableThreadCreator implements Serializable {
    final Logger logger = LoggerFactory.getLogger(getClass());

    private String threadNamePrefix;

    private boolean daemon = false;

    private int priority = Thread.NORM_PRIORITY;

    private ThreadGroup threadGroup;

    private final AtomicInteger threadCount = new AtomicInteger(0);

    public CustomizableThreadCreator() {
        this.threadNamePrefix = getDefaultThreadNamePrefix();
    }

    public CustomizableThreadCreator(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix == null ? getDefaultThreadNamePrefix() : threadNamePrefix;
    }

    protected Thread createThread(Runnable command) {
        Thread thread = new Thread(getThreadGroup(), command, nextThreadName());
        thread.setDaemon(isDaemon());
        thread.setPriority(getPriority());
        return thread;
    }

    public String nextThreadName() {
        return this.getThreadNamePrefix() + threadCount.incrementAndGet();
    }

    public String getDefaultThreadNamePrefix() {
        return ClassUtils.getShortName(getClass()) + "-";
    }


    public boolean isDaemon() {
        return daemon;
    }

    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public void setThreadGroup(ThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
    }

    protected void setThreadGroupName(String threadGroupName) {
        this.threadGroup = new ThreadGroup(threadGroupName);
    }

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = (threadNamePrefix != null ? threadNamePrefix : getDefaultThreadNamePrefix());
    }
}
