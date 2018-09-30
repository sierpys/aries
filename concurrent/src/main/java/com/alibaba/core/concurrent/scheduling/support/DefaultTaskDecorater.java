package com.alibaba.core.concurrent.scheduling.support;

import com.alibaba.core.concurrent.util.ErrorHandler;

/**
 * @author sier.pys 9/26/18
 */
public class DefaultTaskDecorater implements TaskDecorator {

    private ErrorHandler errorHandler = new LoggingErrorHandler();

    @Override
    public Runnable decorate(Runnable task) {
        return new DelegatingErrorHandlingRunnable(task, errorHandler);
    }

}
