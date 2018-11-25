package com.alibaba.accessor.support;

import java.io.IOException;
import java.util.Properties;


public final class PropertiesLoaderSupport {
    public static void loadProperties(Properties props, Resource resources) {
        try {
            PropertiesLoaderUtils.fillProperties(
                    props, resources, new DefaultPropertiesPersister());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
