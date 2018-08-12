package com.alibaba.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sier.pys on 2018/8/11.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public final class Utils {
    private static final Pattern HOST_PORT_PATTERN = Pattern.compile(".*?\\[?([0-9a-zA-Z\\-%._:]*)\\]?:([0-9]+)");


    public static String getHost(String url) {
        Matcher matcher = HOST_PORT_PATTERN.matcher(url);
        return matcher.matches() ? matcher.group(1) : null;
    }

    public static Integer getPort(String url) {
        Matcher matcher = HOST_PORT_PATTERN.matcher(url);
        return matcher.matches() ? Integer.parseInt(matcher.group(2)) : null;
    }

    public static int toPositive(int number) {
        return number & 0x7fffffff;
    }

    public static int murmur2(final byte[] data) {
        int length = data.length;
        int seed = 0x9747b28c;
        // 'm' and 'r' are mixing constants generated offline.
        // They're not really 'magic', they just happen to work well.
        final int m = 0x5bd1e995;
        final int r = 24;

        // Initialize the hash to a random value
        int h = seed ^ length;
        int length4 = length / 4;

        for (int i = 0; i < length4; i++) {
            final int i4 = i * 4;
            int k = (data[i4 + 0] & 0xff) + ((data[i4 + 1] & 0xff) << 8) + ((data[i4 + 2] & 0xff) << 16) + ((data[i4 + 3] & 0xff) << 24);
            k *= m;
            k ^= k >>> r;
            k *= m;
            h *= m;
            h ^= k;
        }

        // Handle the last few bytes of the input array
        switch (length % 4) {
            case 3:
                h ^= (data[(length & ~3) + 2] & 0xff) << 16;
            case 2:
                h ^= (data[(length & ~3) + 1] & 0xff) << 8;
            case 1:
                h ^= data[length & ~3] & 0xff;
                h *= m;
        }

        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;

        return h;
    }

    public static <T> T notNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        } else {
            return t;
        }
    }
}
