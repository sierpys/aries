package com.alibaba.compiler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sier.pys 2019/12/15
 */
public abstract class BaseJDKCompiler<T> implements DynamicCompiler<T> {
    protected static final Pattern PACKAGE_PATTERN = Pattern.compile("package\\s+([_a-zA-Z][_a-zA-Z0-9\\.]*);");

    protected static final Pattern CLASS_PATTERN = Pattern.compile("class\\s+([_a-zA-Z][_a-zA-Z0-9]*)\\s+");

    protected final JavaCompiler compiler;

    protected URLClassLoader parent;
    protected String classpath;

    protected String encoding = "UTF-8";

    protected BaseJDKCompiler() {
        compiler = ToolProvider.getSystemJavaCompiler();
        if (null == compiler) {
            throw new IllegalStateException("can't get java compiler!");
        }
        this.parent = (URLClassLoader) this.getClass().getClassLoader();
        buildClassPath();
    }

    protected String parsePackageName(String sourceCode) {
        Matcher matcher = PACKAGE_PATTERN.matcher(sourceCode);
        String packageName = null;
        if (matcher.find()) {
            packageName = matcher.group(1);
        }
        return packageName;
    }

    protected String parseClassName(String sourceCode) {
        Matcher matcher = CLASS_PATTERN.matcher(sourceCode);
        String className;
        if (matcher.find()) {
            className = matcher.group(1);
        } else {
            throw new IllegalArgumentException("No such class name in " + sourceCode);
        }
        return className;
    }

    protected String parseFullClassName(String sourceCode) {
        String packageName = parsePackageName(sourceCode);
        String className = parseClassName(sourceCode);
        String fullClassName;
        if (packageName == null || packageName.trim().isEmpty()) {
            fullClassName = className;
        } else {
            fullClassName = packageName + "." + className;
        }
        return fullClassName;
    }

    private final void buildClassPath() {
        this.classpath = null;
        StringBuilder sb = new StringBuilder();
        for (URL url : this.parent.getURLs()) {
            String p = url.getFile();
            if ("jar".equals(url.getProtocol())) {
                try {
                    JarURLConnection connection = (JarURLConnection) url.openConnection();
                    final URL jarUrl = connection.getJarFileURL();
                    p = jarUrl.getFile();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            sb.append(p).append(File.pathSeparator);
        }
        this.classpath = sb.toString();
    }
}
