package com.alibaba.core.concurrent;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

/**
 * @author sier.pys 9/30/18
 */
public class Pair<K, V> extends SimpleImmutableEntry<K, V> {

    public Pair(K key, V value) {
        super(key, value);
    }

    public Pair(Map.Entry<? extends K, ? extends V> entry) {
        super(entry);
    }

}
