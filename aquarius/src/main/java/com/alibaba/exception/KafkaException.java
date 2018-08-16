package com.alibaba.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author sier.pys 8/16/18
 */
public class KafkaException extends NestedRuntimeException {


    public KafkaException(String msg) {
        super(msg);
    }

    public KafkaException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
