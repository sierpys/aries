package com.alibaba.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sier.pys 9/15/18
 */
public class OptionalT {
    public static void main(String[] args) {
        Optional<String> optional = Optional.ofNullable(null);
        List<String> list = new ArrayList<>();
        optional.ifPresent(list::add);

        optional.ifPresent(System.out::print);
    }
}
