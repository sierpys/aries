package com.alibaba.mockito.service.impl;

import com.alibaba.mockito.service.DaoService;
import org.springframework.stereotype.Component;

/**
 * @author sier.pys 9/11/18
 */
@Component
public class DaoServiceImpl implements DaoService {
    @Override
    public String findOne() {
        return "not mock";
    }
}
