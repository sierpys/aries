package com.alibaba.core.serializer;

import org.springframework.core.ConfigurableObjectInputStream;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * @author sier.pys 9/14/18
 */
public class DefaultDeserializer implements Deserializer<Object> {
    @Nullable
    private ClassLoader classLoader;

    public DefaultDeserializer() {
        this.classLoader = null;
    }

    public DefaultDeserializer(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Object deserialize(InputStream inputStream) throws IOException {
        ObjectInputStream objectInputStream = new ConfigurableObjectInputStream(inputStream, this.classLoader);
        try {
            return objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Failed to deserializer object type " + e);
        }
    }
}
