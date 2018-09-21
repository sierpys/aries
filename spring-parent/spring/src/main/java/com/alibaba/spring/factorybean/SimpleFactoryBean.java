package com.alibaba.spring.factorybean;

import com.alibaba.spring.S2;
import com.alibaba.spring.impl.S2Impl;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Created by sier.pys on 2018/9/16.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public class SimpleFactoryBean extends AbstractFactoryBean<S2> {
    @Override
    public Class<?> getObjectType() {
        return S2.class;
    }

    @Override
    protected S2 createInstance() throws Exception {
        return new S2Impl();
    }
}
