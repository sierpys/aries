package com.alibaba.core.concurrent.scheduling.support;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author sier.pys 9/26/18
 */
public class ListenableFutureTask<T> extends FutureTask<T> implements ListenableFuture<T> {
    public ListenableFutureTask(Callable<T> callable) {
        super(callable);
    }
}
