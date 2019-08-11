package com.alibaba.other;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author sier.pys 2019-06-22
 */
public class CrawlerCache {
    private final static ConcurrentMap<String, Class<? extends BaseCrawler>> CACHE = new ConcurrentHashMap<>();

    public static void add(String name, Class<? extends BaseCrawler> crawler) {
        CACHE.putIfAbsent(name, crawler);
    }

    public static int size() {
        return CACHE.size();
    }

}
