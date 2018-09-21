package com.alibaba.concurrent;

import org.springframework.scheduling.annotation.Schedules;

import java.util.concurrent.TimeUnit;

/**
 * Created by sier.pys on 2018/8/11.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public interface Scheduler {
    void startUp();

    void shutDown();

    boolean isStarted();

    void schedule(final String name, final Runnable runnable, final Long delay, final Long period, final TimeUnit unit);
}
