package com.alibaba.core.spring.core;

import org.springframework.core.NestedRuntimeException;

/**
 * @author sier.pys 9/22/18
 */
public class KafkaException extends NestedRuntimeException {
    public KafkaException(String msg) {
        super(msg);
    }

    public KafkaException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
