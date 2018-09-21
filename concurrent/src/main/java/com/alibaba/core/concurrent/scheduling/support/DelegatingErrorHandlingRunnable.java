package com.alibaba.core.concurrent.scheduling.support;

import com.alibaba.core.concurrent.util.ErrorHandler;
import org.springframework.util.Assert;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class DelegatingErrorHandlingRunnable implements Runnable {
    private final Runnable delegate;
    private final ErrorHandler errorHandler;

    public DelegatingErrorHandlingRunnable(Runnable delegate, ErrorHandler errorHandler) {
        Assert.notNull(delegate, "Delegate must not be null");
        Assert.notNull(errorHandler, "ErrorHandler must not be null");
        this.delegate = delegate;
        this.errorHandler = errorHandler;
    }

    @Override
    public void run() {
        try {
            this.delegate.run();
        } catch (UndeclaredThrowableException ex) {
            this.errorHandler.handleError(ex.getUndeclaredThrowable());
        } catch (Throwable ex) {
            this.errorHandler.handleError(ex);
        }
    }

    @Override
    public String toString() {
        return "DelegatingErrorHandlingRunnable for " + this.delegate;
    }
}

