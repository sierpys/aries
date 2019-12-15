package com.alibaba.compiler;

import javax.tools.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sier.pys 2019/12/15
 */
public class JDKDynamicCompiler<T> extends BaseJDKCompiler<T> {

    protected JDKDynamicCompiler() {
        super();
    }

    @Override
    public Class<T> compile(String sourceCode) throws Exception {
        String fullClassName = parseFullClassName(sourceCode);
        return compile(sourceCode, fullClassName);
    }

    @Override
    public Class<T> compile(String sourceCode, String className) throws Exception {

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager standardJavaFileManager = compiler.getStandardFileManager(diagnostics, null, null);

        ClassFileManager fileManager = new ClassFileManager(standardJavaFileManager);
        CharSequenceJavaFileObject javaFile = new CharSequenceJavaFileObject(className, sourceCode);

        try {
            List<JavaFileObject> javaFiles = new ArrayList<>();
            javaFiles.add(javaFile);
            List<String> options = new ArrayList<>();
            options.add("-encoding");
            options.add(encoding);
            options.add("-classpath");
            options.add(this.classpath);
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null,
                    javaFiles);
            boolean success = task.call();

            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                    String errorCode = "compile fail: " + diagnostic.getMessage(null) + ". \r\nat " + className
                            + "(" + diagnostic.getLineNumber() + ")" + "\r\ncode:"
                            + javaFile.getLineCode(diagnostic.getLineNumber());
                    throw new java.lang.Exception(errorCode);
                }
            }

            if (!success) {
                String error = compilePrint(diagnostics);
                // System.err.print(error);
                throw new IllegalStateException("Compilation failed. class: " + className + ", diagnostics: \r\n"
                        + error);
            }

            CustomClassLoader<T> dynamicClassLoader = new CustomClassLoader(this.parent);

            for (JavaClassObject javaClassObject : fileManager.getInnerClassJavaClassObject()) {
                Class<T> innerClass = dynamicClassLoader.defineClass(javaClassObject);
                Method method = innerClass.getMethod("print", null);
                method.invoke(innerClass.newInstance(), null);
            }

            JavaClassObject jco = fileManager.getJavaClassObject();
            Class clazz = dynamicClassLoader.defineClass(jco);
            try {
                dynamicClassLoader.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return clazz;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            try {
                javaFile.delete();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                fileManager.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private final String compilePrint(DiagnosticCollector<JavaFileObject> diagnostics) {
        StringBuilder result = new StringBuilder();
        for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
            compilePrint(diagnostic, result);
            result.append("\r\n");
        }
        return result.toString();
    }

    private final void compilePrint(Diagnostic<?> diagnostic, StringBuilder stringBuilder) {
        stringBuilder.append("Code:[" + diagnostic.getCode() + "]\n");
        stringBuilder.append("Kind:[" + diagnostic.getKind() + "]\n");
        stringBuilder.append("Position:[" + diagnostic.getPosition() + "]\n");
        stringBuilder.append("Start Position:[" + diagnostic.getStartPosition() + "]\n");
        stringBuilder.append("End Position:[" + diagnostic.getEndPosition() + "]\n");
        stringBuilder.append("Source:[" + diagnostic.getSource() + "]\n");
        stringBuilder.append("Message:[" + diagnostic.getMessage(null) + "]\n");
        stringBuilder.append("LineNumber:[" + diagnostic.getLineNumber() + "]\n");
        stringBuilder.append("ColumnNumber:[" + diagnostic.getColumnNumber() + "]\n");
    }

}
