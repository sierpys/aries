package com.alibaba.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;

/**
 * @author sier.pys 8/17/18
 */
public class Java8Stream {
    public static void main(String[] args) {
//        Set<String> records = new HashSet<>();
//        records.add("alibaba");
//        records.add("google");
//        records.add("facebook");
//
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("company", "alibaba");
//        Stream<String> stream = records.stream().flatMap(Stream::of);
//        List<String> strings = stream.map(String::toUpperCase).collect(Collectors.toList());
//
//        System.out.println(Arrays.toString(strings.toArray()));

        Stream<String> stream = Stream.of("a", "b", "c");
        boolean match = stream.allMatch(x -> x.length() > 0);
        System.out.println(match);

        List<String> collect = Stream.of("a", "b", "c").map(String::toUpperCase).filter(x -> x.length() > 0).collect(Collectors.toList());
        System.out.println(collect);


        List<Integer> list = Stream.of(asList(1, 2), asList(3, 4)).flatMap(List::stream).collect(Collectors.toList());

        Integer get = Stream.of(1, 2, 3, 4, 5).min(Comparator.comparing(Integer::doubleValue)).get();
        System.out.println(get);

        IntSummaryStatistics summaryStatistics = Stream.of("1", "2", "3", "4").mapToInt(Integer::valueOf).summaryStatistics();
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics.getMax());



    }
}
