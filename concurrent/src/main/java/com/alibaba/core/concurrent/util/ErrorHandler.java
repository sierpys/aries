package com.alibaba.core.concurrent.util;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
@FunctionalInterface
public interface ErrorHandler {
    void handleError(Throwable ex);
}
