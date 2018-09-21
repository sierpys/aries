package com.alibaba.core.concurrent.scheduling.support;

import com.alibaba.core.concurrent.util.ErrorHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class LoggingErrorHandler implements ErrorHandler {
    private final Log logger = LogFactory.getLog(LoggingErrorHandler.class);

    @Override
    public void handleError(Throwable ex) {
        if (logger.isErrorEnabled()) {
            logger.error("Unexpected error occurred in scheduled task", ex);
        }
    }
}
