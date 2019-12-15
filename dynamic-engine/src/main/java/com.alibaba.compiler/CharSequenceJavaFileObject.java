package com.alibaba.compiler;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.net.URI;

/**
 * @author sier.pys 2019/12/15
 */
public class CharSequenceJavaFileObject extends SimpleJavaFileObject {
    private CharSequence content;

    public CharSequenceJavaFileObject(String className,
                                      CharSequence content) {
        super(URI.create("string:///" + className.replace('.', '/')
                + Kind.SOURCE.extension), Kind.SOURCE);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(
            boolean ignoreEncodingErrors) {
        return content;
    }

    /**
     * 获取某个位置的代码
     */
    public String getLineCode(long line) {
        LineNumberReader reader = new LineNumberReader(new StringReader(content.toString()));
        int num = 0;
        String codeLine = null;
        try {
            while ((codeLine = reader.readLine()) != null) {
                num++;
                if (num == line) {
                    break;
                }
            }
        } catch (Throwable ignored) {

        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return codeLine;
    }
}
