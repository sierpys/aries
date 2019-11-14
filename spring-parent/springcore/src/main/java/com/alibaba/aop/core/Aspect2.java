package com.alibaba.aop.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sier.pys 2019-05-25
 */
//@Configuration
public class Aspect2 {
    @Bean
    public AutoWire autoWire() {
        return new AutoWireImpl();
    }
}
