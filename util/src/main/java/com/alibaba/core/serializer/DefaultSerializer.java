package com.alibaba.core.serializer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author sier.pys 9/14/18
 */
public class DefaultSerializer implements Serializer<Object> {

    @Override
    public void serializer(Object input, OutputStream outputStream) throws IOException {
        if (!(input instanceof Serializable)) {
            throw new IllegalArgumentException(getClass().getSimpleName() + "requires a Serializable payload" +
                    "but received an object of type [" + input.getClass().getName() + "]");
        }

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(input);
        objectOutputStream.flush();
    }
}
