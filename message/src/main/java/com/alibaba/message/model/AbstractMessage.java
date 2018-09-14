package com.alibaba.message.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author sier.pys 9/13/18
 */
public abstract class AbstractMessage {
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
