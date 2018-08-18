package com.alibaba.java8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

        List<Integer> integerList = Arrays.asList(new Integer(1),
                new Integer(10), new Integer(200),
                new Integer(101), new Integer(-10),
                new Integer(0));

        integerList.forEach(consumer);

//        map.forEach(biConsumer);


    }

    public static class NewTopic {
        private int partition;
        private String topic;
        private int count;

        public NewTopic(int partition, String topic, int count) {
            this.partition = partition;
            this.topic = topic;
            this.count = count;
        }
    }


    public static void createTopic(Set<String> topics) {
        doWith(admin -> {
            List<NewTopic> newTopics = topics.stream().map(t -> new NewTopic(1, t, 1)).collect(Collectors.toList());
            newTopics.forEach(admin::admin);
        });
    }


    public static void doWith(Consumer<AdminClient> admin) {
        AdminClient adminClient = new AdminClient();
        admin.accept(adminClient);
    }


    public static class AdminClient {
        public void admin(NewTopic input) {
            System.out.println(input);
        }
    }
}

