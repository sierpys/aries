package com.alibaba.core.serializer.support;

import org.springframework.core.NestedRuntimeException;

/**
 * @author sier.pys 9/14/18
 */
public class SerializationFailedException extends NestedRuntimeException {


    public SerializationFailedException(String msg) {
        super(msg);
    }

    public SerializationFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
