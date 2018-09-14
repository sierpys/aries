package com.alibaba.core.serializer.support;

import com.alibaba.core.serializer.DefaultDeserializer;
import com.alibaba.core.serializer.DefaultSerializer;
import com.alibaba.core.serializer.Deserializer;
import com.alibaba.core.serializer.Serializer;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author sier.pys 9/14/18
 */
public class ConverterDelegate implements Serializer<Object>, Deserializer<Object> {
    private final Serializer<Object> serializer;

    private final Deserializer<Object> deserializer;

    public ConverterDelegate(Serializer<Object> serializer, Deserializer<Object> deserializer) {
        Assert.notNull(serializer, "Serializer must not be null");
        Assert.notNull(deserializer, "Deserializer must not be null");
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    public ConverterDelegate(ClassLoader classLoader) {
        this.serializer = new DefaultSerializer();
        this.deserializer = new DefaultDeserializer(classLoader);
    }

    @Override
    public Object deserialize(InputStream inputStream) throws IOException {
        return this.deserializer.deserialize(inputStream);
    }

    @Override
    public void serializer(Object input, OutputStream outputStream) throws IOException {
        this.serializer.serializer(input, outputStream);
    }
}
