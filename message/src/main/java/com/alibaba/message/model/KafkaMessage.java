package com.alibaba.message.model;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Serializable;

/**
 * @author sier.pys 9/13/18
 */
public class KafkaMessage<K extends Serializable, V extends Serializable> extends AbstractMessage<K, V> {
    private ProducerRecord<K, V> producerRecord;

    private KafkaMessage() {
        throw new IllegalArgumentException("no public constructor");
    }


    public KafkaMessage(ProducerRecord<K, V> producerRecord) {
        this.producerRecord = producerRecord;
    }

    @Override
    String getTopic() {
        return producerRecord.topic();
    }

    @Override
    Integer getPartition() {
        return producerRecord.partition();
    }

    @Override
    K getKey() {
        return producerRecord.key();
    }

    @Override
    V getValue() {
        return producerRecord.value();
    }
}
