package com.alibaba.aop.core;

import com.alibaba.aop.core.event.MyListener;
import org.springframework.context.annotation.Bean;

/**
 * @author sier.pys 2019-07-31
 */
@Provider
public class DataProvider {

    public void print() {
        System.out.println("----");
    }

    @Bean
    public AutoWire autoWire() {
        return new AutoWireImpl();
    }

    @Bean
    public MyListener myListener() {
        return new MyListener();
    }

//    @Bean
//    public MyListener_ myListener_() {
//        return new MyListener_();
//    }
}
