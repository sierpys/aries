package com.alibaba.java8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author sier.pys 8/16/18
 */
public class Java8Consumer {
    public static void main(String[] args) {

        BiConsumer<String, String> biConsumer = new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                System.out.println(s + "=======" + s2);
            }
        };


        Map<String, String> map = new HashMap<>();
        map.put("company", "alibaba");


        Consumer<Integer> consumer = (x) -> {
            map.forEach(biConsumer);
            System.out.println(x + 10);
        };

        List<Integer> integerList=Arrays.asList(new Integer(1),
                new Integer(10), new Integer(200),
                new Integer(101), new Integer(-10),
                new Integer(0));

        integerList.forEach(consumer);

//        map.forEach(biConsumer);



    }
}
