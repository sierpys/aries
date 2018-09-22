package com.alibaba.core.spring.core;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author sier.pys 9/22/18
 */
public interface ProducerListener<K, V> {
    default void onSuccess(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata) {
        onSuccess(producerRecord.topic(), producerRecord.partition(), producerRecord.key(), producerRecord.value(), recordMetadata);
    }

    default void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata) {

    }

    default void onError(ProducerRecord<K, V> producerRecord, Exception exception) {
        onError(producerRecord.topic(), producerRecord.partition(), producerRecord.key(), producerRecord.value(), exception);
    }

    default void onError(String topic, Integer partition, K key, V value, Exception exception) {

    }

    default boolean isInterestedInSuccess() {
        return false;
    }
}
