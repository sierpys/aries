package com.alibaba.compiler;

import java.io.*;
import java.lang.reflect.Method;

/**
 * @author sier.pys 2019/12/15
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        JDKDynamicCompiler<Object> compiler = new JDKDynamicCompiler<>();
        File source = new File("/Users/sier/Documents/Apache/aries/dynamic-engine/src/main/java/com.alibaba.compiler/Hello.java");
        StringWriter writer = new StringWriter();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
        String line = null;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
        }
        writer.flush();
        String sourceCode = writer.toString();
        Class<Object> hello = compiler.compile(sourceCode);
        Method method = hello.getMethod("print");
        method.invoke(hello.newInstance(), null);
    }
}
