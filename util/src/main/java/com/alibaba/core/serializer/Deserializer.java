package com.alibaba.core.serializer;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sier.pys 9/14/18
 */
public interface Deserializer<T> {
    T deserialize(InputStream inputStream) throws IOException;
}
