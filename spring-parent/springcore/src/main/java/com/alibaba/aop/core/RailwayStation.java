package com.alibaba.aop.core;

/**
 * @author sier.pys 10/19/18
 */
//@Component
public class RailwayStation {
    private int timeout;
    private int age;
    private String name;

    private RailwayStation() {

    }

    private RailwayStation(int timeout, int age, String name) {
        this.timeout = timeout;
        this.age = age;
        this.name = name;
    }

    public static class Builder {
        private int timeout;
        private int age;
        private String name;


        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public RailwayStation build() {
            return new RailwayStation(timeout, age, name);
        }
    }

    public static void main(String[] args) {
        Builder builder = new Builder();
        RailwayStation railwayStation = builder.setTimeout(10).setAge(10).setName("sier").build();

        System.out.println(railwayStation);
    }

}
