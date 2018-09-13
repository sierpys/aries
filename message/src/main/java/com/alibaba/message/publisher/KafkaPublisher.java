package com.alibaba.message.publisher;

import com.alibaba.message.model.AbstractMessage;
import com.alibaba.message.model.KafkaMessage;
import com.alibaba.message.transport.KafkaMessageTransport;

/**
 * @author sier.pys 9/13/18
 */
public class KafkaPublisher implements Publisher {

    private KafkaMessageTransport kafkaMessageTransport;

    public KafkaPublisher() {
        PublisherManger.registerPublisher(KafkaMessage.class, this);
    }

    @Override
    public boolean sendMessage(AbstractMessage message) {
        return false;
    }
}
