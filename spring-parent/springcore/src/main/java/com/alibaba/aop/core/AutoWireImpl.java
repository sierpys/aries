package com.alibaba.aop.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author sier.pys 11/5/18
 */
@Component
public class AutoWireImpl implements AutoWire {
    Logger logger = LoggerFactory.getLogger("dummy");

    @Override
    public void print() {
        int i =0;
        int[] entry = {1, 2};


        if (logger.isDebugEnabled()) {
            logger.debug("Entry number: {} is {}", i, entry[i]);
        }

        logger.info("name {} nick {} chinese {}", "Alibaba", "阿里巴巴", "阿里巴巴集团");
    }
}
