package com.alibaba.dubbo.core.impl;

import com.alibaba.dubbo.core.DemoService;

/**
 * @author sier.pys 11/4/18
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String name() {
        return "sier.pys";
    }

    @Override
    public String getBox() {
        return "sier";
    }
}
