package com.alibaba.core.concurrent;

import java.util.concurrent.ThreadFactory;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class CustomizableThreadFactory extends CustomizableThreadCreator implements ThreadFactory {
    public CustomizableThreadFactory() {
        super();
    }

    public CustomizableThreadFactory(String threadNamePrefix) {
        super(threadNamePrefix);
    }

    @Override
    public Thread newThread(Runnable r) {
        return createThread(r);
    }
}
