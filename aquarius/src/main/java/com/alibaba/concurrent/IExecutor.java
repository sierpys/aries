package com.alibaba.concurrent;

/**
 * @author sier.pys 8/13/18
 */
public interface IExecutor {
    void startUp();

    void shutDown();

    boolean isRunning();
}
