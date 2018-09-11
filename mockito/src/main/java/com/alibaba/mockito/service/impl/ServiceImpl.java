package com.alibaba.mockito.service.impl;

import com.alibaba.mockito.service.DaoService;
import com.alibaba.mockito.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sier.pys 9/11/18
 */
@Component
public class ServiceImpl implements Service {

    @Autowired
    private DaoService daoService;

    @Override
    public String find() {
        return daoService.findOne();
    }
}
