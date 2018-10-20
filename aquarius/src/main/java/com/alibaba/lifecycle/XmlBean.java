package com.alibaba.lifecycle;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author sier.pys 8/31/18
 */
public class XmlBean {


    @Scheduled(fixedRate = 3)
    public void print() {
        System.out.println("xml bean");
    }
}
