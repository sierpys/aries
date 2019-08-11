package com.alibaba.aop.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sier.pys 2019-07-31
 */
@Component
public class SourceDataProvider {
    @Autowired
    private DataProvider dataProvider;

    public void printSource() {
        System.out.println("print source");
        dataProvider.print();
    }
}
