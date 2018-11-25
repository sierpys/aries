package com.alibaba.accessor.support;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author sier.pys 11/19/18
 */
public class FileSystemResource implements Resource {
    private File file;


    public FileSystemResource(File file) {
        this.file = file;
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
    }


    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(this.file.toPath());
    }
}
