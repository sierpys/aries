package com.alibaba.core.concurrent.scheduling.support;

import com.alibaba.core.concurrent.util.ErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public abstract class TaskUtils {

    public static final ErrorHandler LOG_AND_SUPPRESS_ERROR_HANDLER = new LoggingErrorHandler();

    public static final ErrorHandler LOG_AND_PROPAGATE_ERROR_HANDLER = new PropagatingErrorHandler();


    public static DelegatingErrorHandlingRunnable decorateTaskWithErrorHandler(Runnable task,
                                                                               ErrorHandler errorHandler,
                                                                               boolean isRepeatingTask) {
        if (task instanceof DelegatingErrorHandlingRunnable) {
            return (DelegatingErrorHandlingRunnable) task;
        }

        ErrorHandler eh = (errorHandler != null ? errorHandler : getDefaultErrorHandler(isRepeatingTask));
        return new DelegatingErrorHandlingRunnable(task, eh);
    }

    public static ErrorHandler getDefaultErrorHandler(boolean isRepeatingTask) {
        return (isRepeatingTask ? LOG_AND_SUPPRESS_ERROR_HANDLER : LOG_AND_PROPAGATE_ERROR_HANDLER);
    }


    private static class LoggingErrorHandler implements ErrorHandler {
        private final Logger logger = LoggerFactory.getLogger(LoggingErrorHandler.class);

        @Override
        public void handleError(Throwable ex) {
            if (logger.isErrorEnabled()) {
                logger.error("Unexpected error occurred in scheduled task", ex);
            }
        }
    }

    private static class PropagatingErrorHandler extends LoggingErrorHandler {
        @Override
        public void handleError(Throwable ex) {
            super.handleError(ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
    }

}
