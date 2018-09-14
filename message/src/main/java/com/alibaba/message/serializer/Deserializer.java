package com.alibaba.message.serializer;

/**
 * @author sier.pys 9/14/18
 */
public interface Deserializer<R> {
    R deserializer(Object input);
}
