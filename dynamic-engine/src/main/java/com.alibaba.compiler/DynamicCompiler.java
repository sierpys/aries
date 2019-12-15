package com.alibaba.compiler;

/**
 * @author sier.pys 2019/12/15
 */
public interface DynamicCompiler<T> {
    Class<T> compile(String sourceCode) throws Exception;

    Class<T> compile(String sourceCode, String className) throws Exception;
}
