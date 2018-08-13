package com.alibaba.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sier.pys 8/13/18
 */
public class PC2Thread extends Thread {
    final Logger logger = LoggerFactory.getLogger(getClass());

    public PC2Thread(String name, boolean daemon) {
        super(name);
        configureThread(name, daemon);
    }

    public PC2Thread(String name, Runnable runnable, boolean daemon) {
        super(runnable, name);
        configureThread(name, daemon);
    }

    public PC2Thread nodaemon(String name, Runnable runnable) {
        return new PC2Thread(name, runnable, false);
    }

    public PC2Thread daemon(String name, Runnable runnable) {
        return new PC2Thread(name, runnable, true);
    }


    public void configureThread(final String name, final boolean daemon) {
        super.setDaemon(daemon);
        super.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                logger.error("Thread uncaught Exception `{}`:", name, e);
            }
        });
    }
}
