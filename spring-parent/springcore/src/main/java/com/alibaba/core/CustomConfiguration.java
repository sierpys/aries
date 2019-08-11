package com.alibaba.core;

import com.alibaba.other.BaseCrawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sier.pys 2019-06-22
 */
@Configuration
public class CustomConfiguration {

    @Bean
    public A okttp() {
        return new A();
    }

    @Bean
    public BaseCrawler basicCrawler() {
        return new BasicCrawler();
    }
}
