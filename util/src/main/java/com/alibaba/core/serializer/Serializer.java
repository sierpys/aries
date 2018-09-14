package com.alibaba.core.serializer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author sier.pys 9/14/18
 */
public interface Serializer<T> {
    void serializer(T input, OutputStream outputStream) throws IOException;
}
