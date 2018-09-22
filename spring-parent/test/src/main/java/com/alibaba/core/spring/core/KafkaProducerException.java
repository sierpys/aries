package com.alibaba.core.spring.core;

import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author sier.pys 9/22/18
 */
public class KafkaProducerException extends KafkaException {
    private ProducerRecord producerRecord;

    public KafkaProducerException(String msg) {
        super(msg);
    }

    public KafkaProducerException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public KafkaProducerException(String msg, Throwable ex, ProducerRecord producerRecord) {
        super(msg, ex);
        this.producerRecord = producerRecord;
    }
}
