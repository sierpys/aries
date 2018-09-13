package com.alibaba.message.model;

/**
 * @author sier.pys 9/13/18
 */
public abstract class AbstractMessage<K, V> {
    abstract String getTopic();

    abstract Integer getPartition();

    abstract K getKey();

    abstract V getValue();
}
