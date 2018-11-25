package com.alibaba.spring.clone;

/**
 * @author sier.pys 11/18/18
 */
public interface SerializerHandle<T> {
    byte[] serializer(T obj);

    T deserializer(byte[] bytes);
}
