package com.alibaba.message.publisher;

import com.alibaba.message.binding.AbstractMessageBinding;
import com.alibaba.message.model.AbstractMessage;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sier.pys 9/13/18
 */
public class PublisherManger {
    final static ConcurrentHashMap<Class<? extends AbstractMessage>, Publisher> publishers = new ConcurrentHashMap<>();

    public static void registerPublisher(Class<? extends AbstractMessage> clz, Publisher publisher) {
        publishers.putIfAbsent(clz, publisher);
    }


    public static boolean sendMessage(AbstractMessage message) {
        Publisher publisher = publishers.get(message.getClass());
        if (publisher == null) {
            throw new RuntimeException(String.format("message %s publisher none exist", message.getClass()));
        }
        return publisher.sendMessage(message);
    }

    public static boolean sendMessages(List<AbstractMessage> messages) {
        final boolean[] result = {true};
        messages.forEach(message -> {
            result[0] &= sendMessage(message);
        });
        return result[0];
    }

    public static boolean sendMessages(String message, AbstractMessageBinding... bindings) {
        return sendMessages(message, bindings);
    }

    public static boolean sendMessages(Object objectModel, AbstractMessageBinding... bindings) {
        return sendMessages(AbstractMessageBinding.toMessages(objectModel, bindings));
    }
}
