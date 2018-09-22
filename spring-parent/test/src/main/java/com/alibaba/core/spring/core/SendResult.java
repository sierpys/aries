package com.alibaba.core.spring.core;

import org.apache.kafka.clients.Metadata;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author sier.pys 9/22/18
 */
public class SendResult<K, V> {
    private ProducerRecord<K, V> producerRecord;
    private Metadata metadata;

    public SendResult(ProducerRecord<K, V> producerRecord, Metadata metadata) {
        this.producerRecord = producerRecord;
        this.metadata = metadata;
    }

    public ProducerRecord<K, V> getProducerRecord() {
        return producerRecord;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
