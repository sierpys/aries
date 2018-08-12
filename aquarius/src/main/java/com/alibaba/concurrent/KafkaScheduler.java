package com.alibaba.concurrent;

import com.alibaba.log.LogContext;
import com.alibaba.thread.KafkaThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sier.pys on 2018/8/11.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class KafkaScheduler implements Scheduler {
    final int threads;
    final String schedulerPrefix;
    final boolean deamon;
    final Logger logger;

    private final AtomicInteger schedulerThreadId = new AtomicInteger(0);

    public KafkaScheduler(int threads, String schedulerPrefix, boolean deamon) {
        this.threads = threads;
        this.schedulerPrefix = schedulerPrefix;
        this.deamon = deamon;
        logger = LoggerFactory.getLogger(getClass());
    }

    public KafkaScheduler(int threads, String schedulerPrefix, boolean deamon, LogContext logContext) {
        this.threads = threads;
        this.schedulerPrefix = schedulerPrefix;
        this.deamon = deamon;
        this.logger = logContext.logger(getClass());
    }

    private final static AtomicBoolean started = new AtomicBoolean(false);

    private ScheduledThreadPoolExecutor executor;

    @Override
    public void startUp() {
        logger.info(String.format("This scheduler (`%s`) has been started", getClass().getName()));

        synchronized (this) {
            if (isStarted())
                throw new IllegalStateException("This scheduler has been started!");
            executor = new ScheduledThreadPoolExecutor(this.threads);
            executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
            executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
            executor.setThreadFactory(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new KafkaThread(KafkaScheduler.this.schedulerPrefix + schedulerThreadId.getAndIncrement(), r, KafkaScheduler.this.deamon);
                }
            });
        }
    }

    @Override
    public void shutDown() {
        logger.info(String.format("This scheduler (`%s`) has been shut down", getClass().getName()));
        ScheduledThreadPoolExecutor cachedExecutor = this.executor;
        if (cachedExecutor != null) {
            synchronized (this) {
                cachedExecutor.shutdown();
                this.executor = null;
            }
            try {
                cachedExecutor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public boolean isStarted() {
        synchronized (this) {
            return executor != null;
        }
    }

    @Override
    public void schedule(String name, Runnable runnable, Long delay, Long period, TimeUnit unit) {
        logger.debug(String.format("scheduling task `%s` with init delay `%d`ms and period `%d`", name, TimeUnit.MILLISECONDS.convert(delay, unit),
                TimeUnit.MILLISECONDS.convert(period, unit)));
        synchronized (this) {
            ensureRunning();
            if (period > 0) {
                executor.scheduleAtFixedRate(runnable, delay, period, unit);
            } else {
                executor.schedule(runnable, delay, unit);
            }
        }
    }

    public void scheduleOnce(String name, Runnable runnable) {
        this.schedule(name, runnable, 0L, -1L, TimeUnit.MILLISECONDS);
    }

    public void resizePoolSize(int corePoolSize) {
        this.executor.setCorePoolSize(corePoolSize);
    }

    public void ensureRunning() {
        if (!isStarted()) {
            throw new IllegalStateException("Kafka scheduling not running");
        }
    }
}
