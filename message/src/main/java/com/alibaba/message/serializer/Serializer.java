package com.alibaba.message.serializer;

/**
 * @author sier.pys 9/13/18
 */
public interface Serializer<R> {
    R serialize(Object input);
}
