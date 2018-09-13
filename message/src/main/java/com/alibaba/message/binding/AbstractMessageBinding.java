package com.alibaba.message.binding;

import com.alibaba.message.model.AbstractMessage;
import com.alibaba.message.serializer.Serializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sier.pys 9/13/18
 */
public abstract class AbstractMessageBinding<K, V> {
    protected Serializer<V> serializer;

    public abstract AbstractMessage toMessage(Object input);

    public abstract String getTopic();

    public abstract Integer partition();

    public abstract K getKey();

    public static List<AbstractMessage> toMessages(Object input, AbstractMessageBinding... bindings) {
        List<AbstractMessage> rt = new ArrayList<>(bindings.length);
        for (AbstractMessageBinding binding : bindings) {
            rt.add(binding.toMessage(input));
        }
        return rt;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }
}
