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
        logger.debug("name {} nick {} chinese {}", "Eirc", "Àº∂˚", "≈À”¿ §");
    }
}
