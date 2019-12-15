package com.alibaba.compiler;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author sier.pys 2019/12/15
 */
public class CustomClassLoader<T> extends URLClassLoader {


    public CustomClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    public static String getClassFullName(JavaClassObject javaClassObject) {
        String name = javaClassObject.getName();
        name = name.substring(1, name.length() - 6);
        name = name.replace("/", ".");
        return name;
    }

    @SuppressWarnings("unchecked")
    public Class<T> defineClass(JavaClassObject javaClassObject) {
        String name = getClassFullName(javaClassObject);
        byte[] classData = javaClassObject.getBytes();
        return (Class<T>) super.defineClass(name, classData, 0, classData.length);
    }
}
