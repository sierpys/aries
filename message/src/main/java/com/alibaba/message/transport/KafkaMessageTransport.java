package com.alibaba.message.transport;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;

/**
 * @author sier.pys 9/13/18
 */
public class KafkaMessageTransport implements DisposableBean, InitializingBean {

    private Map<String, KafkaProducer> senderMap;

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
