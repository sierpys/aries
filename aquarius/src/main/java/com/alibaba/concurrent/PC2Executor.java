package com.alibaba.concurrent;

import com.alibaba.thread.PC2Thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author sier.pys 8/13/18
 */
public class PC2Executor implements IExecutor {

    final static AtomicBoolean RUNNING_STATE = new AtomicBoolean(false);

    final ExecutorService executorService = Executors.newFixedThreadPool(10, new NamedThreadFactory("PC2Executor", true));
    final BlockingQueue<PC2Thread> blockingQueue = new LinkedBlockingQueue<>(124);

    @Override
    public void startUp() {
        if (RUNNING_STATE.compareAndSet(false, true)) {

        }
    }

    @Override
    public void shutDown() {

    }

    @Override
    public boolean isRunning() {
        return RUNNING_STATE.get();
    }


}
