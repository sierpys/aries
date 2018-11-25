package com.alibaba.accessor.support;

import java.io.*;
import java.util.Properties;

/**
 * @author sier.pys 11/19/18
 */
public interface PropertiesPersister {
    void load(Properties props, InputStream is) throws IOException;
}
