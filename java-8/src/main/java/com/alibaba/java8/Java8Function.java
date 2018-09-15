package com.alibaba.java8;

import java.util.function.Function;

/**
 * @author sier.pys 8/16/18
 */
public class Java8Function {
    public static void main(String[] args) {
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        };


        Function<Integer, String> func = new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) {
                return integer.toString();
            }
        };


        Function<Integer, Integer> compose = function.compose(func);

        Integer apply = compose.apply(100);

        System.err.printf("=============");

        Function<Integer, String> fun = (x) -> x.toString();


        System.out.println(fun.apply(100));

    }
}
