package com.alibaba.core.serializer.support;

import com.alibaba.core.converter.Converter;
import com.alibaba.core.serializer.DefaultDeserializer;
import com.alibaba.core.serializer.Deserializer;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;

/**
 * @author sier.pys 9/14/18
 */
public class DeserializeingConverter implements Converter<byte[], Object> {

    private Deserializer<Object> deserializer;

    public DeserializeingConverter(Deserializer<Object> deserializer) {
        Assert.notNull(deserializer, "Deserializer must not be null");
        this.deserializer = deserializer;
    }

    public DeserializeingConverter() {
        this.deserializer = new DefaultDeserializer();
    }


    public DeserializeingConverter(ClassLoader classLoader) {
        this.deserializer = new DefaultDeserializer(classLoader);
    }

    @Override
    public Object convert(byte[] source) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(source);
        try {
            return this.deserializer.deserialize(byteArrayInputStream);
        } catch (Throwable ex) {
            throw new SerializationFailedException("Failed to deserialize payload" + "Is the byte array a result of " +
                    "corresponding serialization for " + this.deserializer.getClass().getSimpleName() + "?", ex);
        }
    }
}
