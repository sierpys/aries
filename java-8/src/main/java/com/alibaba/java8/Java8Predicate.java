package com.alibaba.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author sier.pys 8/16/18
 */
public class Java8Predicate {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        names.add("Harry");
        names.add("Daniel");
        names.add("Lucifer");
        names.add("April O' Neil");


        Stream<String> stream = names.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("Harry");
            }
        });

        Optional<String> any = stream.findAny();
        if (any.isPresent()) {
            System.out.println(any.get());
        }

        Optional<String> notNUll = Optional.of("alibaba");
        notNUll.ifPresent((x)->{
            x = x + 10;
            System.out.println(x);
        });
    }
}
