package com.alibaba.core.concurrent.scheduling.support;

/**
 * @author sier.pys 9/26/18
 */
@FunctionalInterface
public interface TaskDecorator {
    Runnable decorate(Runnable task);
}
