package com.alibaba.message.model;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Serializable;

/**
 * @author sier.pys 9/13/18
 */
public class KafkaMessage<K extends Serializable, V extends Serializable> extends AbstractMessage {
    private final ProducerRecord<K, V> producerRecord;

    private KafkaMessage() {
        throw new IllegalArgumentException("no public constructor");
    }


    public KafkaMessage(ProducerRecord<K, V> producerRecord) {
        this.producerRecord = producerRecord;
    }

    String getTopic() {
        return producerRecord.topic();
    }

    Integer getPartition() {
        return producerRecord.partition();
    }

    K getKey() {
        return producerRecord.key();
    }

    V getValue() {
        return producerRecord.value();
    }
}
