package com.alibaba.core.concurrent.scheduling.support;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class PropagatingErrorHandler extends LoggingErrorHandler {
    private final Logger logger = LoggerFactory.getLogger(PropagatingErrorHandler.class);

    @Override
    public void handleError(Throwable ex) {
        super.handleError(ex);

        ReflectionUtils.rethrowRuntimeException(ex);
    }
}
