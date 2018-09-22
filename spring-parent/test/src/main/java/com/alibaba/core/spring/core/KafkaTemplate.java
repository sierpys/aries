package com.alibaba.core.spring.core;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

/**
 * @author sier.pys 9/22/18
 */
public class KafkaTemplate<K, V> {
    private DefaultKafkaProducerFactory<K, V> kafkaProducerFactory;

    private ProducerListener<K, V> producerListener = new LoggingProducerListener<>();

    public void setProducerListener(ProducerListener<K, V> producerListener) {
        Assert.state(producerListener != null, "ProducerListener must not be null");
        this.producerListener = producerListener;
    }

    protected ListenableFuture<SendResult<K, V>> doSend(final ProducerRecord<K, V> producerRecord) {
        Producer<K, V> producer = getProducer();
        SettableListenableFuture<SendResult<K, V>> settableFuture = new SettableListenableFuture<>();
        producer.send(producerRecord, (recordMetadata, e) -> {
            try {
                if (e == null) {
                    settableFuture.set(new SendResult<>(producerRecord, recordMetadata));
                    this.producerListener.onSuccess(producerRecord, recordMetadata);
                } else {
                    settableFuture.setException(e);
                    this.producerListener.onError(producerRecord, e);
                }
            } catch (Exception ex) {
                closeProducer(producer, false);
            }
        });

        return settableFuture;
    }


    private Producer<K, V> getProducer() {
        return this.kafkaProducerFactory.createProducer();
    }

    protected void closeProducer(Producer<K, V> producer, boolean inLocalTx) {
        if (!inLocalTx) {
            producer.close();
        }
    }
}
