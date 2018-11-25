package com.alibaba.accessor.support;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sier.pys 11/19/18
 */
public interface InputStreamSource {
    InputStream getInputStream() throws IOException;
}
