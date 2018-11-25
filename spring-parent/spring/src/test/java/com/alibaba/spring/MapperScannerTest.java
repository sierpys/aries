package com.alibaba.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author sier.pys 11/21/18
 */
public class MapperScannerTest {
    @Test
    public void testScanner() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyBatisConfigRef.class);
        applicationContext.refresh();
        applicationContext.start();
    }

    @MapperScan
    public static class MyBatisConfigRef {

    }
}
