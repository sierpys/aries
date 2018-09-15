package com.alibaba.spring.impl;

import com.alibaba.spring.Service;

/**
 * @author sier.pys 9/15/18
 */
public class ServiceImpl implements Service {
    @Override
    public String find() {
        return "service Impl";
    }
}
