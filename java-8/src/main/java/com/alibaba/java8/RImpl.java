package com.alibaba.java8;

/**
 * @author sier.pys 9/15/18
 */
public class RImpl implements R1, R2 {

    @Override
    public void rock() {
        R1.super.rock();
        System.out.println("sajdklf");
    }

    public static void main(String[] args) {
        R2 r = new RImpl();
        r.rock();
    }
}
