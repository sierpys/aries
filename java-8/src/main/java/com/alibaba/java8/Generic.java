package com.alibaba.java8;

import java.util.function.Supplier;

/**
 * @author sier.pys 9/15/18
 */
public class Generic<K, V> {

    public Generic<K, V> create(Supplier<Generic<K, V>> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        Generic<String, String> generic = new Generic<>();
        Generic<String, String> generic1 = generic.create(Generic::new);

    }
}
