package com.alibaba.core.converter;

/**
 * @author sier.pys 9/14/18
 */
public interface Converter<S, T> {
    T convert(S source);
}
