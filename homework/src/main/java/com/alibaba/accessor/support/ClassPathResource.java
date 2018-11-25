package com.alibaba.accessor.support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sier.pys 11/19/18
 */
public class ClassPathResource implements Resource {
    private String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this.path = path;
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = null;
        if (this.classLoader != null) {
            is = this.classLoader.getResourceAsStream(this.path);
        } else {
            is = ClassLoader.getSystemResourceAsStream(this.path);
        }
        if (is == null) {
            throw new FileNotFoundException(String.format("%s cannot be opened because it does not exist", path));
        }
        return is;
    }
}
