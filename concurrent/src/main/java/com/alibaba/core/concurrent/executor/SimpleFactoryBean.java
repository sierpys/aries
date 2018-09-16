package com.alibaba.core.concurrent.executor;

import org.checkerframework.checker.nullness.compatqual.NonNullType;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class SimpleFactoryBean extends AbstractFactoryBean<Simple> {


    @Override
    public Class<?> getObjectType() {
        return Simple.class;
    }

    @Override
    @NonNullType

    protected Simple createInstance() throws Exception {
        return new Simple();
    }
}
