package com.alibaba.spring.clone;

import java.io.*;

/**
 * @author sier.pys 11/18/18
 */
public class DefaultSerializerHandle<T> implements SerializerHandle<T> {
    @Override
    public byte[] serializer(T obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream(512);
            ObjectOutputStream objstream = new ObjectOutputStream(stream);
            objstream.writeObject(obj);
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private void serializer(T obj, OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("objectOutputStream must not be null");
        } else {
            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(obj);
            } catch (IOException e) {
                throw new SerializeFailException("Failed to serialize obj", e);
            } finally {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public T deserializer(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("");
        }
        return deserializer((InputStream) (new ByteArrayInputStream(bytes)));
    }


    private T deserializer(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("");
        }
        ObjectInputStream objectStream = null;
        Object object = null;
        try {
            objectStream = new ObjectInputStream(inputStream);
            object = objectStream.readObject();
            return (T) object;
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectStream != null) {
                try {
                    objectStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
        return null;
    }
}
