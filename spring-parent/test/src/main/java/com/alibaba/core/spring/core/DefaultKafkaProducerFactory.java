package com.alibaba.core.spring.core;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.Lifecycle;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author sier.pys 9/22/18
 */
public class DefaultKafkaProducerFactory<K, V> implements KafkaFactory<K, V>, Lifecycle, DisposableBean {
    private static final int DEFAULT_PHYSICAL_CLOSE_TIMEOUT = 30;

    private final static Logger logger = LoggerFactory.getLogger(DefaultKafkaProducerFactory.class);

    private volatile boolean running;

    private Map<String, Object> configs;

    private volatile CloseSafeProducer<K, V> producer;

    private Serializer<K> keySerializer;

    private Serializer<V> valueSerializer;

    private final BlockingQueue<CloseSafeProducer<K, V>> cache = new LinkedBlockingDeque<>();

    private int physicalCloseTimeout = DEFAULT_PHYSICAL_CLOSE_TIMEOUT;

    public DefaultKafkaProducerFactory(Map<String, Object> configs) {
        this(configs, null, null);
    }

    public DefaultKafkaProducerFactory(Map<String, Object> configs, Serializer<K> keySerializer, Serializer<V> valueSerializer) {
        this.configs = configs;
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;
    }

    @Override
    public Producer<K, V> createProducer() {
        if (this.producer == null) {
            synchronized (this) {
                this.producer = new CloseSafeProducer<>(createKafkaProducer(), cache);
            }
        }
        return this.producer;
    }


    private Producer<K, V> createKafkaProducer() {
        return new KafkaProducer<>(this.configs, this.keySerializer, this.valueSerializer);
    }

    public Map<String, Object> getConfigurationProperties() {
        return Collections.unmodifiableMap(this.configs);
    }

    @Override
    public void destroy() throws Exception {
        CloseSafeProducer<K, V> producer = this.producer;
        this.producer = null;
        if (producer != null) {
            producer.delegate.close();
        }
        producer = this.cache.poll();
        while (producer != null) {
            try {
                producer.delegate.close(this.physicalCloseTimeout, TimeUnit.SECONDS);
            } catch (Exception e) {
                logger.error("Unexpected error occur whiling closing producer", e);
            }
            producer = this.cache.poll();
        }
    }

    @Override
    public void start() {
        this.running = true;
    }

    @Override
    public void stop() {
        try {
            destroy();
        } catch (Exception e) {
            logger.error("Unexpected error while closing produer", e);
        }
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    private static class CloseSafeProducer<K, V> implements Producer<K, V> {
        private final Producer<K, V> delegate;
        private final BlockingQueue<DefaultKafkaProducerFactory.CloseSafeProducer<K, V>> cache;

        private volatile boolean txFailed;

        public CloseSafeProducer(Producer<K, V> delegate, BlockingQueue<CloseSafeProducer<K, V>> cache) {
            this.delegate = delegate;
            this.cache = cache;
        }

        @Override
        public void initTransactions() {
            this.delegate.initTransactions();
        }

        @Override
        public void beginTransaction() throws ProducerFencedException {
            try {
                this.delegate.beginTransaction();
            } catch (ProducerFencedException e) {
                this.txFailed = true;
                throw e;
            }
        }

        @Override
        public void sendOffsetsToTransaction(Map<TopicPartition, OffsetAndMetadata> map, String s) throws ProducerFencedException {
            this.delegate.sendOffsetsToTransaction(map, s);
        }

        @Override
        public void commitTransaction() throws ProducerFencedException {
            try {
                this.delegate.commitTransaction();
            } catch (ProducerFencedException e) {
                this.txFailed = true;
                throw e;
            }
        }

        @Override
        public void abortTransaction() throws ProducerFencedException {
            try {
                this.delegate.abortTransaction();
            } catch (ProducerFencedException e) {
                this.txFailed = true;
                throw e;
            }
        }

        @Override
        public Future<RecordMetadata> send(ProducerRecord<K, V> producerRecord) {
            return this.delegate.send(producerRecord);
        }

        @Override
        public Future<RecordMetadata> send(ProducerRecord<K, V> producerRecord, Callback callback) {
            return this.delegate.send(producerRecord, callback);
        }

        @Override
        public void flush() {
            this.delegate.flush();
        }

        @Override
        public List<PartitionInfo> partitionsFor(String s) {
            return this.delegate.partitionsFor(s);
        }

        @Override
        public Map<MetricName, ? extends Metric> metrics() {
            return this.delegate.metrics();
        }

        @Override
        public void close() {
            if (this.cache == null) {
                return;
            }
            if (this.txFailed) {
                logger.warn("Error during transactional operation; producer removed from cache; possible cause: "
                        + "broker restarted during transaction");
                this.delegate.close();
            } else {
                synchronized (this) {
                    if (!this.cache.contains(this)) {
                        this.cache.offer(this);
                    }
                }
            }
        }

        @Override
        public void close(long l, TimeUnit timeUnit) {
            close();
        }

    }
}
