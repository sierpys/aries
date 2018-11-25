package com.alibaba.accessor.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public abstract class PropertiesLoaderUtils {

    static void fillProperties(Properties props, Resource resource, PropertiesPersister persister)
            throws IOException {

        InputStream stream = null;
        try {
            stream = resource.getInputStream();
            persister.load(props, stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
