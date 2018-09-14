package com.alibaba.core.serializer.support;

import com.alibaba.core.converter.Converter;
import com.alibaba.core.serializer.DefaultSerializer;
import com.alibaba.core.serializer.Serializer;
import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;

/**
 * @author sier.pys 9/14/18
 */
public class SerializingConverter implements Converter<Object, byte[]> {

    private Serializer<Object> serializer;

    public SerializingConverter() {
        this.serializer = new DefaultSerializer();
    }

    public SerializingConverter(Serializer<Object> serializer) {
        Assert.notNull(serializer, "Serializer must not be null");
        this.serializer = serializer;
    }

    @Override
    public byte[] convert(Object source) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1024);
        try {
            this.serializer.serializer(source, byteStream);
            return byteStream.toByteArray();
        } catch (Throwable ex) {
            throw new SerializationFailedException("Failed to serialize object using " + this.serializer.getClass().getName(), ex);
        }

    }
}
