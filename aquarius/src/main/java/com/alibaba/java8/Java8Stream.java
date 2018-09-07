package com.alibaba.java8;

import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sier.pys 8/17/18
 */
public class Java8Stream {
    public static void main(String[] args) {
        Set<String> records = new HashSet<>();
        records.add("alibaba");
        records.add("google");
        records.add("facebook");


        HashMap<String, String> map = new HashMap<>();
        map.put("company", "alibaba");
        Stream<String> stream = records.stream().flatMap(Stream::of);
        List<String> strings = stream.map(String::toUpperCase).collect(Collectors.toList());

        System.out.println(Arrays.toString(strings.toArray()));
        Assert.notNull(map, () -> "not null");
//        records.stream().flatMap(p -> records.records(p).stream()).map(r -> r.topic() + "-" + r.partition() + "@" + r.offset()).collect(Collectors.toList()))
    }
}
