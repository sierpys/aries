package com.alibaba.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sier.pys on 2018/8/11.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class KafkaThread extends Thread {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public KafkaThread(final String name, final Runnable runnable, final boolean daemon) {
        super(runnable, name);
        configureThread(name, daemon);
    }

    public KafkaThread(final String name, final boolean daemon) {
        super(name);
        configureThread(name, daemon);
    }

    public static KafkaThread daemon(final String name, final Runnable runnable) {
        return new KafkaThread(name, runnable, true);
    }

    public static KafkaThread nodaemon(final String name, final Runnable runnable) {
        return new KafkaThread(name, runnable, false);
    }


    private void configureThread(final String name, final boolean daemon) {
        setDaemon(daemon);
        setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                logger.error("UncaughtException in thread '{}':", name, e);
            }
        });
    }
}
