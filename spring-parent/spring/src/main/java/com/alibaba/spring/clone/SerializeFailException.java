package com.alibaba.spring.clone;

import org.springframework.core.NestedRuntimeException;

/**
 * @author sier.pys 11/18/18
 */
public class SerializeFailException extends NestedRuntimeException {
    public SerializeFailException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SerializeFailException(String msg) {
        super(msg);
    }
}
