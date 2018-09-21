package com.alibaba.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sier.pys 9/19/18
 */
public class CollectorsT {
    public static void main(String[] args) {
        List<Integer> asList =
                Arrays.asList(1, 2, 3, 4, 5);

    }


    public static class Wrapper {
        public Integer delegate;

        public Wrapper(Integer delegate) {
            this.delegate = delegate;
        }
    }
}
