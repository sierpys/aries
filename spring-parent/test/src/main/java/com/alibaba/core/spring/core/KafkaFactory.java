package com.alibaba.core.spring.core;


import org.apache.kafka.clients.producer.Producer;

/**
 * @author sier.pys 9/22/18
 */
public interface KafkaFactory<K, V> {
    Producer<K, V> createProducer();

    default boolean transactionCapable() {
        return false;
    }
}
