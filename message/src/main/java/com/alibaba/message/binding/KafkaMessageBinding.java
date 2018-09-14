package com.alibaba.message.binding;

import com.alibaba.message.model.AbstractMessage;
import com.alibaba.message.model.KafkaMessage;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Serializable;

/**
 * @author sier.pys 9/13/18
 */
public class KafkaMessageBinding<K extends Serializable, V extends Serializable> extends AbstractMessageBinding<V> {

    private String topic;

    private K key;

    private Integer partition;

    @Override
    public AbstractMessage toMessage(Object input) {
        V value = serializer.serialize(input);
        ProducerRecord<K, V> producerRecord = new ProducerRecord<K, V>(getTopic(), getKey(), value);
        return new KafkaMessage<>(producerRecord);
    }

    public String getTopic() {
        return this.topic;
    }

    public Integer partition() {
        return this.partition;
    }

    public K getKey() {
        return this.key;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }
}
