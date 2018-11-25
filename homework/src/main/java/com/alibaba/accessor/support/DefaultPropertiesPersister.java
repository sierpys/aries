package com.alibaba.accessor.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author sier.pys 11/19/18
 */
public class DefaultPropertiesPersister implements PropertiesPersister {
    @Override
    public void load(Properties props, InputStream is) throws IOException {
        props.load(is);
    }
}
